package TalkBoxConfig;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class Sound {
	
	
	
	private TargetDataLine targetLine;
	public String temp;
	
	
	public Sound() {
		
		this.targetLine = null;
		
		
	}
	

	public void SoundFormart() throws LineUnavailableException {
		
		
		try {
		
		AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
		
		
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		
		if(!AudioSystem.isLineSupported(info)) {
			
			System.err.println("Line is not supported");
			
		}
	
		this.targetLine = (TargetDataLine) AudioSystem.getLine(info);
		this.targetLine.open();
		
		System.out.println("Start Recording");
		targetLine.start();
		
		}
		
		catch(LineUnavailableException lue) {
			
			lue.printStackTrace();
			
		}
		
	}

	
	public void start() throws InterruptedException {
		
		try {

		Thread thread = new Thread() {
			
			
		 public void run()  {
				
				AudioInputStream audioStream = new AudioInputStream(targetLine);
				File audioFile = new File(temp + ".wav");
				try {
				AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, audioFile);
				}
				catch(IOException ioe) {
					
					ioe.printStackTrace();
				}
				System.out.println("Stopped Recording");
				
			}
		 
		};
		
		
		thread.start();
		System.out.println("Recording");
		Thread.sleep(Integer.MAX_VALUE); // This is the max the recorder can play but the user will never play that long
		stop();
		}
		catch(InterruptedException ie) {
			
			ie.printStackTrace();
		}
		
	}
	
	public void stop() {
	this.targetLine.stop();
	this.targetLine.close();
	}
	
}	
