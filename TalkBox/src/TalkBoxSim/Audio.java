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

import TalkBoxConfig.AudioHandler;
import TalkBoxConfig.TalkBoxConfiguration;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;

public class Audio {
	
	
	private TalkBoxConfiguration tbc;
	private int count;
	private String src;
	boolean collide;
	private Clip clip;
	public boolean result = false;
	
	public Audio() {
		
		this.src= "src/Audio/";
		this.clip=null;
		this.collide=false;
		this.count=0;
		
		}
	
	
	
	
	
	  public void AudioToButton(GridPane pane,ArrayList<Button> buttons,TreeView<String> profile,int row) throws Exception {
		  int size = profile.getRoot().getChildren().get(row).getChildren().size();
		  ArrayList<String> al = new ArrayList<String>();
		  for(int k = 0; k < size; k++) {
			  al.add(profile.getRoot().getChildren().get(row).getChildren().get(k).getValue());}
		  for(int i = 0; i < size; i++) {
			  String name = al.get(i);
			  buttons.get(i).setText(name);
			  buttons.get(i).setOnAction(e -> this.handle(src + name +".wav"));
			  ;}
	  }
	 
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
	  