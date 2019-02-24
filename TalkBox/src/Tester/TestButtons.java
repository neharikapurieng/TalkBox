package Tester;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;


import TalkBoxConfig.ButtonGui;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

class TestButtons extends ButtonGui  {
	
	public  ButtonGui buttons;
	
	

	



	@Test
	void testNumOfButtons()  {
		
		buttons = new ButtonGui();
		
		assertEquals(buttons.getNumofButton(),0);
		
		
		}
	
	

	
	



}
