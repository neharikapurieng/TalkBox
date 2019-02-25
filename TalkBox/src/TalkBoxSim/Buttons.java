package TalkBoxSim;

import java.util.ArrayList;

import javax.sound.sampled.Clip;

import TalkBoxConfig.Serializer;
import TalkBoxConfig.TalkBoxConfiguration;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


/*
 * Button class for the simulator 
 * Deserializes the information from TalkBoxData 
 */
public class Buttons {

	TalkBoxConfiguration tbc;	
	public ArrayList<Button> Buttons = new ArrayList<Button>();
	boolean collide = false;
	Clip clip;
	Audio ad;
	public Button set;
	/*
	 * Adds the amounts of buttons from TalkBoxData.tbc to an ArrayList
	 */
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
	

	public ArrayList<Button> getButtonList() {
		return this.Buttons;
	}
	


	  public void Adder(GridPane p) {
		  int ctr = 0;
		  int count = tbc.NumOfAudioButtons;
		  for(int j = 0; j <= Math.ceil(tbc.NumOfAudioButtons/5);j++) {
				 if(count >= 5) {
					 for(int k = 0; k < 5; k++) {
						 Buttons.get(ctr).setMinSize(75, 75);
						 GridPane.setHgrow(Buttons.get(ctr), Priority.ALWAYS);
						 GridPane.setVgrow(Buttons.get(ctr),Priority.ALWAYS);
						 p.setVgap(5);
						 p.add(this.Buttons.get(ctr), k, j);
						 ctr++;
						 count--;
					 }
				 }
				 else {
					 for(int h = 0; h < count; h++) {
						 Buttons.get(ctr).setMinSize(75, 75);
						 GridPane.setHgrow(Buttons.get(ctr), Priority.ALWAYS);
						 GridPane.setVgrow(Buttons.get(ctr),Priority.ALWAYS);
						 p.setVgap(5);
						 p.add(this.Buttons.get(ctr), h, j);
					
						 ctr++;
				 }
			 }
			 }
	  }
	
	  public void SetProfile(VBox p) {
		  set = new Button("Set Profile");
		  set.setMinSize(250, 50);
		  p.getChildren().add(set);
	  }
	

	// will let the user know, if the file is not found 
/*
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
	*/
	
}
