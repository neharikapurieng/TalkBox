package Tester;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;


import TalkBoxConfig.ButtonConfig;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

class TestButtons extends ButtonConfig  {
	
	public  ButtonConfig buttons;
	
	

	



	@Test
	void testNumOfButtons()  {
		
		buttons = new ButtonConfig();
		
		assertEquals(buttons.getNumofButton(),0);
		
		
		}
	
	

	
	



}
