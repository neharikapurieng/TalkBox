package Tester;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TalkBoxConfig.AudioHandler;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

class AudioHandlerTest {

	@Test
	void test() {

	
		 AudioHandler b = new AudioHandler("recordings/default_name.wav");
//		 EventType<MouseEvent> event = MouseEvent.MOUSE_CLICKED;
		 ActionEvent e = new ActionEvent();
		 b.handle(e);

		 assertTrue(b.isActive());
		 
	
	
	}

}
