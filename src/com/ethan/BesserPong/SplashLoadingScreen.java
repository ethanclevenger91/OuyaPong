package com.ethan.BesserPong;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

import com.ethan.framework.Game;
import com.ethan.framework.Graphics;
import com.ethan.framework.Graphics.ImageFormat;
import com.ethan.framework.Screen;

public class SplashLoadingScreen extends Screen {
	
	private static final int FADE_MILLISECONDS = 2000;
	private static final int FADE_STEP = FADE_MILLISECONDS/255;
	
	private int alpha = 1;
	private boolean first = true;
	private boolean fadeIn = true;
	private boolean paused = false;

	public SplashLoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.splash = g.newImage("splash.jpg",  ImageFormat.RGB565);
		if(alpha > 0) {
			if(fadeIn) {
				 alpha += 5;
				if(alpha >= 255) {
					alpha = 255;
					fadeIn = false;
					paused = true;
				}
				SystemClock.sleep(2);
			}
			else if(paused) {
			    paused = false; 
			    SystemClock.sleep(1000);
			}
			else if(!fadeIn && !paused) {
				alpha -= 5;
				if(alpha<0) {alpha = 0;}
				SystemClock.sleep(2);
			}
		}
		else {
			alpha = 0;
			game.setScreen(new LoadingScreen(game));
		}
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.clearScreen(Color.BLACK);
		g.drawFadeImage(Assets.splash, 0, 0, alpha);
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
