package TalkBoxConfig;


import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ButtonGui{
	
	

	private int ctr2;
	public ArrayList<CustomButton> BList;
    private int count;
    private String temp1;
    private String temp2;
    private int time;
	
	public ButtonGui() {
		
		this.BList=null;
		this.ctr2=0;
	}
	
	
	  public void buttonAdder(int n,GridPane sp) throws IllegalArgumentException {
		  try {
			  this.BList= new ArrayList<CustomButton>();;
		  sp.getChildren().clear();
			   for(int i = ctr2; i < n; i++) {
		    	   String buttonname = String.format("Sound %d", i+1);
		    	  
		    	   BList.add( new CustomButton(buttonname));
		    	   ctr2++;
		       }
			   this.ctr2 = 0;
			   int count = n;
			 for(int j = 0; j <= Math.ceil(n/5);j++) {
				 if(count >= 5) {
					 for(int k = 0; k < 5; k++) {
						 BList.get(ctr2).setMinSize(75, 75);
						 GridPane.setHgrow(BList.get(ctr2), Priority.ALWAYS);
						 GridPane.setVgrow(BList.get(ctr2), Priority.ALWAYS);
						sp.setVgap(5);
						 sp.add(BList.get(ctr2), k, j);
						 ctr2++;
						 count--;}}
				 else {
					 for(int h = 0; h < count; h++) {
						 BList.get(ctr2).setMinSize(75, 75);
						 GridPane.setHgrow(BList.get(ctr2), Priority.ALWAYS);
						 GridPane.setVgrow(BList.get(ctr2), Priority.ALWAYS);
						 sp.add(BList.get(ctr2), h, j);
						 sp.setVgap(5);
						 ctr2++; }}}}
		  catch(IllegalArgumentException io) {
			 System.out.println("Wrong input");
			  
		  }
		  }
	  
  public ArrayList<CustomButton> getArray() {
		  return this.BList;
		  }
  
  
  
	public void getFirstSound(int size) {

		

			for (count = 0; count <= size - 1; count++) {

				CustomButton button = this.BList.get(count);
				button.setOnAction(e -> {

					this.temp1 = button.getString();
					System.out.println("This is the first one" +this.temp1);
					getSecondSound(size);
					// System.out.println(button.getString());

				});
				
			}
		}
	
	public void getSecondSound(int size) {
	
	
		
			for (count = 0; count <= size - 1; count++) {

				CustomButton button = this.BList.get(count);
				button.setOnAction(e -> {

					this.temp2 = button.getString();
					System.out.println("This is the second one" + this.temp2);
                 
					

				});

			}

	   }
	
	
 public int getNumofButton() {
	 
	 return this.ctr2;
 }




}

