package com.ethan.framework;

import java.util.Map;

public interface Game {

	public Audio getAudio();

    public FileIO getFileIO();

    public Graphics getGraphics();
    
    public Input getInput();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getInitScreen();
    
    public void setOption(int option, int choice);
    
    public int getOption(int option);
    
    public Map<Integer, Integer> getOptions();
	
}
