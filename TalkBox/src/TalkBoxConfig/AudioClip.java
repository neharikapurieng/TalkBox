package TalkBoxConfig;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import TalkBoxConfig.FileInputOutput;

public class AudioClip {
	

	private Clip clip = null;

	private String clipPath;
	
	
	public AudioClip(String filePath) {
		
		this.clipPath = filePath;
		
	
		
	}
	
	
	
	public boolean isConnecting() {
		
		return this.clip.isOpen();
	}
	

	
	 
	
	  /*
 	   * plays audio files.
	   * 
	   */

	  public void play() {	

			
			if(this.clip != null && this.clip.isRunning()) {
				this.clip.stop();
				this.clip.flush();
			}
			try {
				
				FileInputStream clipFile = (FileInputStream) FileInputOutput.getWavFile(this.clipPath);
				
				BufferedInputStream bufferedClipFile = new BufferedInputStream(clipFile);
				
				
				AudioInputStream audio = AudioSystem.getAudioInputStream(bufferedClipFile);

				this.clip = AudioSystem.getClip();

				clip.open(audio);
				
				clip.start();
			}

			catch(Exception e) {	

				System.out.println("Can't find audio file: message = " + e.getMessage());
				System.out.println("Path used:" + this.clipPath);
			}

		}
	}
