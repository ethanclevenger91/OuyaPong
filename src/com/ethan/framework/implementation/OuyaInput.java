package com.ethan.framework.implementation;

import java.util.ArrayList;
import java.util.List;

import tv.ouya.console.api.OuyaController;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;

import com.ethan.framework.Input;

public class OuyaInput implements Input {
	

	@Override
	public List<Integer> getButtonsPressed(int player) {

		List<Integer> buttons = new ArrayList<Integer>();
		OuyaController c = OuyaController.getControllerByPlayer(player);
		if (c.buttonChangedThisFrame(OuyaController.BUTTON_O)) {
			Log.v("button", "pressed");
		    if (c.buttonPressedThisFrame(OuyaController.BUTTON_O)) {
		        buttons.add(OuyaController.BUTTON_O);
		    }
		}
		else {
			//Log.v("no_button", "uhuh");
		}

		return buttons;
	}    

   
    
}
