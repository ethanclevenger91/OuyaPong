package com.ethan.BesserPong;

import java.util.Random;

import android.util.Log;

public class Ball {
	
	private float friction = (float)0.06;
	final int MOVESPEED = 10;
	private int centerX=200;
	private int centerY = 50;
	private float speedX = MOVESPEED;
	private float speedY = 3;
	private int radius = 10;

	public Ball() {
		centerX = 640;
		Random rand = new Random();
		centerY = 80+rand.nextInt(560);
		boolean positiveX = true;
		if(rand.nextInt(2) == 0) {
			positiveX = false;
		}
		speedX = MOVESPEED;
		if(!positiveX) speedX = -speedX;
		boolean positiveY = true;
		if(rand.nextInt(2) == 0) {
			positiveY = false;
		}
		speedY = (float) ((Math.random()/10) + 3);
		if(!positiveY) speedY = -speedY;
	}
	
	public void update(boolean reset) {
		if(reset) {
			centerX = 640;
			Random rand = new Random();
			centerY = 80+rand.nextInt(560);
			boolean positiveX = true;
			if(rand.nextInt(2) == 0) {
				positiveX = false;
			}
			speedX = MOVESPEED;
			if(!positiveX) speedX = -speedX;
			boolean positiveY = true;
			if(rand.nextInt(2) == 0) {
				positiveY = false;
			}
			speedY = (float) ((Math.random()) + 3);
			if(!positiveY) speedY = -speedY;
		}
		else {
			centerY += speedY;
			centerX += speedX;
			if(centerY + speedY <= 80) {
				centerY = 80;
				speedY = -speedY;
			}
			else if(centerY + speedY >= 640) {
				centerY = 640;
				speedY = -speedY;
			}
		}
	}
	
	public String score(Paddle p1, Paddle p2) {
		if(centerX <= p1.getCenterX()-p1.getWidth()/2 ) {
			return "p2";
		}
		else if(centerX >= p2.getCenterX() + p2.getWidth()/2) {
			return "p1";
		}
		else return "none";
	}
	
	public void intersects(Paddle p) {
		int distx = Math.abs(centerX - p.getCenterX());
	    int disty = Math.abs(centerY - p.getCenterY());
	    if ((distx > (p.getWidth()/2 + radius)) || (disty > (p.getHeight()/2 + radius))) { 
	    	return; 
	    }

	    if ((distx <= (p.getWidth()/2)) || (disty <= (p.getHeight()/2))) {
	    	speedX = -speedX;
	    	speedY = p.getSpeedY()*friction + speedY;
	    	if(p.getCenterX() > 600) {
	    		centerX = p.getCenterX()- (p.getWidth()/2) - radius - 1;
	    	}
	    	else {
	    		centerX = p.getCenterX() + (p.getWidth()/2) + radius+1;
	    	}
	    	return;
	    }

	    int cornerDistance_sq = (((p.getCenterX()+p.getWidth()/2)-centerX)^2 +
	    		((p.getCenterY()+p.getHeight()/2)-centerY)^2);

	    if (cornerDistance_sq <= (radius^2)) {
	    	speedX = -speedX;
	    	speedY = p.getSpeedY()*friction + speedY; 
	    	if(p.getCenterX() > 600) {
	    		centerX = p.getCenterX()- (p.getWidth()/2) - radius - 1;
	    	}
	    	else {
	    		centerX = p.getCenterX() + (p.getWidth()/2) + radius+1;
	    	}
	    	return;
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
    
    public int getRadius(){
    	return radius;
    }
    
}
