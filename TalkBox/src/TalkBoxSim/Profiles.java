package TalkBoxSim;

import java.util.Arrays;

import TalkBoxConfig.Serializer;
import TalkBoxConfig.TalkBoxConfiguration;
import javafx.application.Application;

public class Profiles {
	
	private TalkBoxConfiguration tbc;
	
	
	public Profiles() {
		//TalkBoxConfiguration tbc;
		this.tbc=null;
	}

	//TalkBoxConfiguration tbc;
	public void profiles() throws Exception {
		tbc = (TalkBoxConfiguration) Serializer.Load("bin/TalkBoxData/TalkBoxData.tbc");
		
		/*  public String[][] audioFiles(){
			  String[][] temp = new String[root.getChildren().size()][BList.size()];
			  for(int i = 0; i < root.getChildren().size(); i++) {
			  int numofAudio = TItems.get(i).getChildren().size();
				for(int j = 0; j < numofAudio; j++) {
					temp[i][j] = TItems.get(i).getChildren().get(j).toString();}}
			  for(String[] row : temp) {
				  System.out.println(Arrays.toString(row));}
			return temp;}
		  */
		  
		 String[][] temp = tbc.getAudioFileNames();
		 
		 for(int i=0; i<=temp.length-1; i++) {
			 
			  int profileLength = temp[i].length; 
			 
			 for(int j=0; j<=profileLength-1; j++) {
				 
				 System.out.println(temp[i][j] + "Hello");
				 
			 }
			 
			 
		 }
	
	
		
		
		
		
		
	}
	
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	
		
	    Profiles profile = new Profiles();
	    profile.profiles();

		
	}

}
