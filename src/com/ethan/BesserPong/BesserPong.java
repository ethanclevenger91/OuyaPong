package com.ethan.BesserPong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import tv.ouya.console.api.OuyaController;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.ethan.BesserPong.R;
import com.ethan.framework.Screen;
import com.ethan.framework.implementation.OuyaGame;

public class BesserPong extends OuyaGame {

	boolean firstTimeCreate = true;
	
	public void onCreate() {
		super.onCreate(null);
	}

	@Override
	public Screen getInitScreen() {
		if(firstTimeCreate) {
			Assets.load(this, this.getOption(0));
			firstTimeCreate = false;
		}
		return new SplashLoadingScreen(this);
	}
	
	private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		
		String line = null;
		try {
			while((line = reader.readLine()) != null) {
				sb.append((line + "\n"));
			}
		} catch (IOException e) {
			Log.w("LOG", e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				Log.w("LOG", e.getMessage());
			}
		}
		return sb.toString();
	}
	
	@Override 
	public void onResume() {
		super.onResume();
		Assets.theme.play();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Assets.theme.pause();
	}

}
