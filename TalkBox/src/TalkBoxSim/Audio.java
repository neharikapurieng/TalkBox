package TalkBoxSim;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import TalkBoxConfig.TalkBoxConfiguration;
import javafx.scene.layout.GridPane;

public class Audio {
	
	
	private TalkBoxConfiguration tbc;
	private int count;
	private String src = "src/Audio/";
	boolean collide = false;
	private Clip clip;
	
	public Audio() {
		
		
		}
	
	
	
	
	
	  public void AudioToButton(GridPane pane) throws Exception {
			
		  Profiles profile = new Profiles();
		  profile.LaunchProfileDisplay();
		  Buttons button = new Buttons();
		  button.Adder(pane);
		  int size = profile.getRoot().getChildren().get(profile.getRow()).getChildren().size();
		  System.out.println(size);
		  ArrayList<String> al = new ArrayList<String>();
		  for(int k = 0; k < size; k++) {
			  al.add(profile.getRoot().getChildren().get(profile.getRow()).getChildren().get(k).getValue());}
	
		  
		  for(int i = 0; i < size; i++) {
			  count++;
			  String name = al.get(i);
			  button.getButtonList().get(i).setText(name);
			  button.getButtonList().get(i).setOnAction(e -> handle(src + name + ".wav"));
			  System.out.println(button.getButtonList().get(i));
			  
			  
			  ;}}
	  
	  
	  public void handle(String s) {	// Play Audio Files and checks if it exists
			if(this.collide == true) this.clip.stop();
			try {
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File(s));
				this.clip = AudioSystem.getClip();
				clip.open(audio);
				clip.start();
				this.collide = true;}
			catch(Exception e) {	
				System.out.println("Can't find audio file");}}
	  
	  
	
	
	
	
	

}
