package com.ethan.BesserPong;

import android.util.Log;

public class Paddle {
	
	final int MOVESPEED = 9;
	private int centerX;
	private int centerY = 50;
	private int width=20;
	private int height=100;
	private boolean movingDown = false;
	private boolean movingUp = false;
	private float speedX = 0;
	private float speedY = 0;
	
	public Paddle(int playerNum) {
		switch(playerNum) {
		case 1:
			centerX = 150;
			break;
		case 2:
			centerX = 1130;
		}
	}
	
	public void update() {
		centerY += speedY;
		if(centerY + speedY <= 120) {
			centerY = 121;
		}
		else if(centerY + speedY >= 600) {
			centerY = 600;
		}
	}
	
	public void moveDown(float tilt) {

		setMovingDown(true);
		speedY = MOVESPEED*tilt;
	}
	
	public void moveUp(float tilt) {
		setMovingUp(true);
		speedY = MOVESPEED*tilt;
	}
	
	public void stopDown() {
		setMovingDown(false);
		stop();
	}
	
	public void stopUp() {
		setMovingUp(false);
		stop();
	}
	
	public void stop() {
		if (isMovingDown() == false && isMovingUp() == false) {
			speedY = 0;
		}
	}
	
	public float getSpeedX() {
		return speedX;
	}
	
	public float getSpeedY() {
		return speedY;
	}
	
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isMovingDown() {
		return movingDown;
	}
	
	public boolean isMovingUp() {
		return movingUp;
	}
	
	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
	}
	
	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}
	
	public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }
    
    public int getWidth() {
    	return width;
    }
    
    public int getHeight() {
    	return height;
    }
    
}
