package TalkBoxConfig;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

class GuiConfig2Test {

	public GridPane gridpane;
	public MenuItem menuitem;
	public GuiConfig2 gui;
	
	@BeforeEach
	public void setUpGridPane() throws Exception{
		
	     gridpane = new GridPane();
	     gridpane.setVisible(true);
	}
	
	@Test
	
	public void testClickMenubutton() throws InterruptedException {
		
		Thread.sleep(1000);
		gui.ClickMenuButton();
		Thread.sleep(1000);
		
	}
    @Test
    public void testClickProfileText() throws InterruptedException {
    	Thread.sleep(1000);
    	gui.clickProfileText();
    	Thread.sleep(1000);
    	
    	
    }
    
    
    

}
