import java.awt.Color;
import java.awt.Font;

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
		config.num1.setBackground(Color.yellow);
		config.num2.setBackground(Color.yellow);
		config.num3.setBackground(Color.yellow);
		config.num4.setBackground(Color.yellow);
		config.num5.setBackground(Color.yellow);
		config.num6.setBackground(Color.yellow);
		config.num7.setBackground(Color.green);
		config.num7.setFont(new Font("Arial", Font.PLAIN, 20));
		config.num8.setFont(new Font("Arial", Font.PLAIN, 35));

		
	}
	

}
