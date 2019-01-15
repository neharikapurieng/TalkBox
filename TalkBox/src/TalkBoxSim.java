import javax.swing.*;
public class TalkBoxSim {
	
	public static void main(String [] args) {
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			new TalkBoxSim();	
		}
	});
	}
		
	public TalkBoxSim() {
		TalkBoxConfig config = new TalkBoxConfig();
	}

}
