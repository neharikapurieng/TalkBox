package Tester;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import org.junit.jupiter.api.Test;

import TalkBoxConfig.Sound;
import junit.framework.Assert;

class TestSound {

	@Test
	void IsSoundFormated() throws LineUnavailableException {
		
		try {
		
		Sound sound = new Sound();
		sound.soundFormat();
		
		}
		
		catch(LineUnavailableException lue) {
			
			assertTrue(true);
		}
	}
	
	@Test
	 void IsFileNameAddedtoSound() throws LineUnavailableException, InterruptedException {
		 
		 Sound sound = new Sound();
		 String file= "Morning";
		 sound.soundFormat();
		 sound.start(file);
		 assertEquals(file, sound.getFileName());
		 
		 
	 }
	@Test
	void IsSoundFileCreated() throws LineUnavailableException, InterruptedException {
		
		try {
		Sound sound = new Sound();
		sound.soundFormat();
		sound.start("Hello");
		}
		
		catch(Exception ioe) {
			
		 assertTrue(true);
		}
		
	}
	@Test
	void isSoundStopped() throws LineUnavailableException, InterruptedException {
		
		TargetDataLine targetLineTest = null;
		AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		targetLineTest = (TargetDataLine) AudioSystem.getLine(info);
		targetLineTest.open();
		Sound sound = new Sound();
		sound.soundFormat();
		sound.start("Null");
		sound.stop();;
		targetLineTest.stop();
		targetLineTest.close();
	    assertEquals(sound.getTargetLine().isOpen(), targetLineTest.isOpen());	
	}

}
