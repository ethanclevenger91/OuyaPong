package com.ethan.BesserPong;

import android.content.Context;
import android.util.Log;

import com.ethan.framework.Game;
import com.ethan.framework.Graphics;
import com.ethan.framework.Graphics.ImageFormat;
import com.ethan.framework.Screen;

public class LoadingScreen extends Screen {


	public LoadingScreen(Game game) {
		super(game);

	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
		
		Assets.background = g.newImage("splash.jpg", ImageFormat.ARGB4444);
		
		Assets.background_landscape = g.newImage("landscape.jpg", ImageFormat.RGB565);
		Assets.background_boat = g.newImage("boat.jpg", ImageFormat.RGB565);
		Assets.background_mountain = g.newImage("mountains.jpg", ImageFormat.RGB565);
		
		Assets.options_menu = g.newImage("options.jpg", ImageFormat.RGB565);
		
		Assets.paddle = g.newImage("paddle.png", ImageFormat.RGB565);
		
		Assets.button = g.newImage("button.jpg", ImageFormat.RGB565);
		
		Assets.ball = g.newImage("ball.png",  ImageFormat.RGB565);
		
		Assets.options_arrow = g.newImage("option-arrow.png", ImageFormat.RGB565);
		
		//Assets.click = game.getAudio().createSound("explode.ogg");
		
		game.setScreen(new MainMenuScreen(game));
		
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		//g.drawImage(Assets.splash, 0, 0);
		
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
