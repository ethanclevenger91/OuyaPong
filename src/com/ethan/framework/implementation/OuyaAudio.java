package com.ethan.framework.implementation;

import java.io.IOException;

import com.ethan.framework.Audio;
import com.ethan.framework.Music;
import com.ethan.framework.Sound;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

public class OuyaAudio implements Audio {
	AssetManager assets;
	SoundPool soundPool;
	
	public OuyaAudio(Activity activity) {
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	}
	
	@Override
	public Music createMusic(String filename) {
		try {
			AssetFileDescriptor assetDescriptor = assets.openFd(filename);
			return new OuyaMusic(assetDescriptor);
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load music '" + filename + "'");
		}
	}

	public OuyaAudio() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Sound createSound(String file) {
		try {
            AssetFileDescriptor assetDescriptor = assets.openFd(file);
            int soundId = soundPool.load(assetDescriptor, 0);
            return new OuyaSound(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load sound '" + file + "'");
        }
	}

}
