package TalkBoxSim;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import TalkBoxConfig.Serializer;
import TalkBoxConfig.TalkBoxConfiguration;
import javafx.scene.control.Button;

public class Buttons {

	TalkBoxConfiguration tbc;	
	public ArrayList<Button> Buttons = new ArrayList<>();
	boolean collide = false;
	Clip clip;
	public Buttons() {
		  try {
			tbc = (TalkBoxConfiguration) Serializer.Load("bin/TalkBoxData/TalkBoxData.tbc");
			for(int i = 0; i < tbc.getNumberOfAudioButtons(); i++) {
				String BName = String.format("Sound %d", i);
				Buttons.add(new Button(BName));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		  System.out.println(tbc.NumOfAudioSets);
	}
	
	
	  public void handle(String s) {	
			if(this.collide == true) this.clip.stop();
			try {
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File(s));
				this.clip = AudioSystem.getClip();
				clip.open(audio);
				clip.start();
				this.collide = true;
					}
			catch(Exception e) {	
				System.out.println("Can't find audio file");
			}
		}
	
	
}
