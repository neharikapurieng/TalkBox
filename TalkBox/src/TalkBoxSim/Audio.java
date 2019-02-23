package TalkBoxSim;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
	private String src;
	boolean collide;
	private Clip clip;
	
	public Audio() {
		
		this.src= "src/Audio/";
		this.clip=null;
		this.collide=false;
		this.count=0;
		
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
			  button.getButtonList().get(i).setOnAction(e -> this.handle(src + name +".wav"));
			  System.out.println(button.getButtonList().get(i));
			  System.out.println(src + name+ ".wav");
			  
			  
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
<<<<<<< HEAD
	  
=======
>>>>>>> branch 'master' of https://github.com/neharikapurieng/TalkBox
}
	  