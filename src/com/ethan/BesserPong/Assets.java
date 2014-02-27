package com.ethan.BesserPong;

import android.util.Log;

import com.ethan.framework.Image;
import com.ethan.framework.Music;
import com.ethan.framework.Sound;

public class Assets {
	
	public static Image menu, splash, background, background_landscape, background_boat, background_mountain, options_menu;
	public static Image button;
	public static Image paddle, options_arrow;
	public static Sound click;
	public static Music theme;
	public static Image ball;
	
	public static void load(BesserPong besserPong, int option) {
		if(theme != null) {
			theme.stop();
		}
		switch(option) {
		case 0:
			theme = besserPong.getAudio().createMusic("menutheme.mp3");
			break;
		case 1:
			theme = besserPong.getAudio().createMusic("dropsofH20.mp3");
			break;
		case 2:
			theme = besserPong.getAudio().createMusic("funky-nurykabe.mp3");
			break;
		case 3:
			theme = besserPong.getAudio().createMusic("black-rainbow.mp3");
			break;
		case 4:
			theme = besserPong.getAudio().createMusic("undercover.mp3");
			break;
		case 5:
			theme = besserPong.getAudio().createMusic("test-drive.mp3");
			break;
		case 6:
			theme = besserPong.getAudio().createMusic("wooh-yeah.mp3");
			break;
		case 7:
			theme = besserPong.getAudio().createMusic("xylophone.mp3");
			break;
		case 8:
			theme = null;
		}
		if(theme != null) {
			theme.setLooping(true);
			theme.setVolume(.85f);
			theme.play();
		}
	}

}
