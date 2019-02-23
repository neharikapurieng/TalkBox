package Tester;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;


import TalkBoxConfig.ButtonGui;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

class TestButtons extends ButtonGui  {
	
	public static ButtonGui buttons;
	
	
	@Before
	public void setup() throws Exception {
		ButtonGui.launch(ButtonGui.class);
	}



	@Test
	void testNumOfButtons() throws InterruptedException {
		int n = 7;
	
		ArrayList<Button> buttonList = new ArrayList<Button>();
		for(int i=0; i<7; i++) {
			
			buttonList.add(new Button());
			
		}
		TestButtons.buttons = new ButtonGui();
	
		TestButtons.buttons.buttonAdder(n);
		Thread.sleep(1000);
		assertTrue(buttons.getArray().size()== buttonList.size());
		//assertEquals(6, 6);
		
	}

	
	



}
