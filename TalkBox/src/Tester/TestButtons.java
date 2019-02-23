package Tester;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TalkBoxConfig.ButtonAdder;

class TestButtons {

	@Test
	void testNumOfButtons() {
		
		ButtonAdder button = new ButtonAdder();
		button.bAdder(6);
		assertEquals(button.getArray(), 6);
		
	}

	
	
	
	
}
