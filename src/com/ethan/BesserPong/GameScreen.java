package com.ethan.BesserPong;

import java.util.ArrayList;
import java.util.List;

import tv.ouya.console.api.OuyaController;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.animation.Animation;

import com.ethan.framework.Game;
import com.ethan.framework.Graphics;
import com.ethan.framework.Image;
import com.ethan.framework.Input;
import com.ethan.framework.Screen;

public class GameScreen extends Screen {
	
	enum GameState {
		Running, Paused, GameOver
	}
	
	GameState state = GameState.Running;
	private static Paddle playerOne;
	private static Paddle playerTwo;
	private static Ball theBall;
	int scoreOne = 0;
	int scoreTwo = 0;
	Paint paint, paint2;

	public GameScreen(Game game) {
		super(game);
		
		playerOne = new Paddle(1);
		playerTwo = new Paddle(2);
		theBall = new Ball();
		
		paint = new Paint();
		paint.setTextSize(100);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);

	}

	@Override
	public void update(float deltaTime) {
		OuyaController.startOfFrame();
		if(state == GameState.Running){
			updateRunning(deltaTime);
		}
	}
	
	private void updateRunning(float deltaTime) {
		theBall.intersects(playerOne);
		theBall.intersects(playerTwo);
		String scored = theBall.score(playerOne, playerTwo);
		boolean resetBall = false;
		if(scored == "p1") {
			scoreOne += 1;
			resetBall = true;
		}
		else if(scored == "p2") {
			scoreTwo += 1;
			resetBall = true;
		}
		playerOne.update();
		playerTwo.update();
		theBall.update(resetBall);
		for(int i = 0; i<2; i++) {
			OuyaController c = OuyaController.getControllerByPlayer(i);
			if(c != null) {
				if(c.getButton(OuyaController.BUTTON_A)) {
					game.setScreen(new MainMenuScreen(game));
				}
				float axisY = c.getAxisValue(OuyaController.AXIS_LS_Y);
				if(axisY > OuyaController.STICK_DEADZONE) {
					switch(i) {
					case 0:
						playerOne.moveDown(axisY);
						break;
					case 1:
						playerTwo.moveDown(axisY);
						break;
					}
				}
				else if(axisY < -OuyaController.STICK_DEADZONE) {
					switch(i) {
					case 0:
						playerOne.moveUp(axisY);
						break;
					case 1:
						playerTwo.moveUp(axisY);
						break;
					}
				}
				else {
					switch(i) {
					case 0:
						playerOne.stopDown();
						playerOne.stopUp();
						break;
					case 1:
						playerTwo.stopDown();
						playerTwo.stopUp();
					}
				}
			}
		}
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.clearScreen(Color.BLACK);
		switch(game.getOption(1)) {
		
		case 0:
			break;
		case 1:
			g.drawImage(Assets.background_landscape, 0, 0);
			break;
		case 2:
			g.drawImage(Assets.background_boat, 0, 0);
			break;
		case 3:
			g.drawImage(Assets.background_mountain, 0, 0);
			break;
		}
		g.drawLine(640, 0, 640, 720, Color.WHITE);
		g.drawLine(0, 70, 1280, 70, Color.WHITE);
		g.drawLine(0, 650, 1280, 650, Color.WHITE);
		
		g.drawImage(Assets.paddle, playerOne.getCenterX() - 10, 
				playerOne.getCenterY() - 50);
		g.drawImage(Assets.paddle, playerTwo.getCenterX() - 10,
				playerTwo.getCenterY() - 50);
		g.drawImage(Assets.ball, theBall.getCenterX()-10, theBall.getCenterY() - 10);
		g.drawString(Integer.toString(scoreOne), 426, 160, paint);
		g.drawString(Integer.toString(scoreTwo), 853, 160, paint);
		
	}

	@Override
	public void pause() {
		if(state == GameState.Running) {
			state = GameState.Paused;
		}
		else if(state == GameState.Paused){
			state = GameState.Running;
		}
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub
		
	}

}
