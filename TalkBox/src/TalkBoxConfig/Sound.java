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
				File audioFile = new File("S.wav");
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
		Thread.sleep(10000); // This is how long the recorder will play in milliseconds
		}
		catch(InterruptedException ie) {
			
			ie.printStackTrace();
		}
		
	}
	
	public void stop() {
		
	
	this.targetLine.stop();
	this.targetLine.close();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Starting Sound Test...");
		

			Sound sound = new Sound();
			try {
				sound.SoundFormart();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				sound.start();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sound.stop();
				
		System.out.println("Stopped Recording");
				
		
		}
		

}	
