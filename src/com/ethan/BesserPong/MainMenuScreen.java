package com.ethan.BesserPong;

import java.util.List;

import tv.ouya.console.api.OuyaController;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import com.ethan.framework.Game;
import com.ethan.framework.Graphics;
import com.ethan.framework.Input;
import com.ethan.framework.Screen;

public class MainMenuScreen extends Screen {
	

	public MainMenuScreen(Game game) {
		super(game);
		SystemClock.sleep(300);
	}

	@Override
	public void update(float deltaTime) {
		OuyaController.startOfFrame();
		Input input = game.getInput();
		//List<Integer> buttons = input.getButtonsPressed(0);
		OuyaController c = OuyaController.getControllerByPlayer(0);
		if(c!=null) {
			if(c.getButton(OuyaController.BUTTON_O)) {
			//if(buttons.contains(OuyaController.BUTTON_O)) {
				game.setScreen(new GameScreen(game));
			}
			else if(c.getButton(OuyaController.BUTTON_Y)) {
				game.setScreen(new OptionsScreen(game));
			}
		}
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
	    g.drawImage(Assets.menu, 0, 0);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
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
