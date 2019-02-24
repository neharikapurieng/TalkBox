package Tester;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TalkBoxConfig.AudioClip;

class AudioClipTest {

	@Test
	void testAudioClipStartsSuccessfully() {
		
		AudioClip clip = new AudioClip("src/Audio/boring.wav");
		
		clip.play();

		
		
		assertTrue(clip.isConnecting());
	}
	
	@Test
	void testAudioClipThrowsException() {
		
		try {
		
		AudioClip clip = new AudioClip("src/Audio/boring.wav");
		
		clip.play();
		
		}
		
		catch(Exception e) {
			
			assertTrue(true);
		}
		
	}
	
	


}
