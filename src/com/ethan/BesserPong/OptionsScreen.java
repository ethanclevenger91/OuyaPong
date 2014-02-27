package com.ethan.BesserPong;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import tv.ouya.console.api.OuyaController;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.util.Log;

import com.ethan.framework.Game;
import com.ethan.framework.Graphics;
import com.ethan.framework.Input;
import com.ethan.framework.Screen;

public class OptionsScreen extends Screen {
	Paint paint;
	int musicOptions = 9;
	int backgroundOptions = 4;
	int selection = 0;
	int selection_limit = 2; 

	public OptionsScreen(Game game) {
		super(game);
		paint = new Paint();
		paint.setTextSize(85);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		SystemClock.sleep(100);
	}

	@Override
	public void update(float deltaTime) {
		OuyaController.startOfFrame();
		//List<Integer> buttons = input.getButtonsPressed(0);
		OuyaController c = OuyaController.getControllerByPlayer(0);
		if(c.getButton(OuyaController.BUTTON_A)) {
			SharedPreferences sharedPref = ((Activity) game).getPreferences(Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedPref.edit();
			for(int i=0; i<selection_limit; i++) {
				editor.putInt(Integer.toString(i), game.getOption(i));
			}
			editor.commit();
			game.setScreen(new MainMenuScreen(game));
		}
		else {
			float axisY = c.getAxisValue(OuyaController.AXIS_LS_Y);
			float axisX = c.getAxisValue(OuyaController.AXIS_LS_X);
			if(Math.abs(axisY) > OuyaController.STICK_DEADZONE) {
				if(axisY > 0) {
					selection--;
					if(selection < 0) selection = selection_limit-1;
				}
				else if(axisY < 0) {
					selection++;
					if(selection >= selection_limit) selection = 0;
				}
			}
			if(Math.abs(axisX) > OuyaController.STICK_DEADZONE) {
				int curr = game.getOption(selection);
				if(axisX > 0) curr++;
				else if(axisX < 0) curr--;
				if(selection == 0) {
					if(curr >= musicOptions) curr = 0;
					else if(curr<0) curr = musicOptions-1;
					Assets.load((BesserPong) game, curr);
				}
				else if(selection == 1) {
					if(curr >= backgroundOptions) curr = 0;
					else if(curr<0) curr = backgroundOptions-1;
				}
				game.setOption(selection, curr);
			}
			SystemClock.sleep(100);
		}
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.clearScreen(Color.BLACK);
		g.drawImage(Assets.options_menu, 0, 0);
		switch(selection) {
		case 0:
			g.drawImage(Assets.options_arrow, 530, 280);
			g.drawHorFlipImage(Assets.options_arrow, 750, 290);
			break;
		case 1:
			g.drawImage(Assets.options_arrow, 530, 450);
			g.drawHorFlipImage(Assets.options_arrow, 750, 450);
			break;
		}
		
		g.drawString(Integer.toString(game.getOption(0)+1), 640, 340, paint);
		g.drawString(Integer.toString(game.getOption(1)+1), 640, 500, paint);
		
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
