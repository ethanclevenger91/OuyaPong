package com.ethan.framework.implementation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import tv.ouya.console.api.OuyaController;

import com.ethan.framework.Audio;
import com.ethan.framework.FileIO;
import com.ethan.framework.Game;
import com.ethan.framework.Graphics;
import com.ethan.framework.Input;
import com.ethan.framework.Screen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.KeyEvent;
import android.view.MotionEvent;

public abstract class OuyaGame extends Activity implements Game {
	OuyaFastRenderView renderView;
	Graphics graphics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Screen screen;
	Map<Integer, Integer> options;
	
	@SuppressLint("UseSparseArrays")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		OuyaController.init(this);
		
		options = new HashMap<Integer, Integer>();
		SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
		try {
			Map<String, ?> savedOptions = sharedPref.getAll();
			Iterator it = savedOptions.entrySet().iterator();
			while (it.hasNext()) {
			    @SuppressWarnings("unchecked")
				Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>)it.next();
			    this.setOption(Integer.parseInt(pair.getKey()), pair.getValue());
			}
		}
		catch(NullPointerException e) {
		}
		
		int frameBufferWidth = 1280;
		int frameBufferHeight = 720;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Config.RGB_565);
		
		renderView = new OuyaFastRenderView(this, frameBuffer);
		graphics = new OuyaGraphics(getAssets(), frameBuffer);
		fileIO = new OuyaFileIO(this);
		input = new OuyaInput();
		audio = new OuyaAudio(this);
		screen = getInitScreen();
		setContentView(renderView);
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    boolean handled = OuyaController.onKeyDown(keyCode, event);
	    return handled || super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
	    boolean handled = OuyaController.onKeyUp(keyCode, event);
	    return handled || super.onKeyUp(keyCode, event);
	}

	@Override
	public boolean onGenericMotionEvent(MotionEvent event) {
	    boolean handled = OuyaController.onGenericMotionEvent(event);
	    return handled || super.onGenericMotionEvent(event);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		screen.resume();
		renderView.resume();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		renderView.pause();
		screen.pause();
		
		if(isFinishing()) {
			screen.dispose();
		}
	}
	
	@Override
	public FileIO getFileIO() {
		return fileIO;
	}
	
	@Override
	public Graphics getGraphics() {
		return graphics;
	}
	
	@Override
	public Input getInput() {
		return input;
	}
	
	@Override
	public Audio getAudio() {
		return audio;
	}
	
	@Override
	public void setScreen(Screen screen) {
		if(screen==null) {
			throw new IllegalArgumentException("Screen must not be null");
		}
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen=screen;
	}
	
	public void setOption(int option, int choice) {
		options.put(option, choice);
	}
	
	//If option is not found, getOption will add the option and return 0.
	public int getOption(int option) {
		if(options.get(option)!=null) {
			return options.get(option);
		}
		else {
			options.put(option, 0);
			return 0;
		}
	}
	
	public Map<Integer, Integer> getOptions() {
		return options;
	}
	
	public Screen getCurrentScreen() {
		return screen;
	}
	
	public OuyaGame() {
		// TODO Auto-generated constructor stub
	}

}
