package Tester;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TalkBoxConfig.AudioClip;

class AudioClipTest {

	@Test
	void testAudioClipStartsSuccessfully() {
		
		AudioClip clip = new AudioClip("recordings/default_name.wav");
		
		clip.play();
		
		
		assertTrue(clip.isConnecting());
	}

}
