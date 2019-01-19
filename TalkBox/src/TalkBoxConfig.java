import javax.swing.*;

public class TalkBoxConfig {
	
	public JPanel panel;
	
	public JButton num1;
	public JButton num2;
	public JButton num3;
	public JButton num4;
	public JButton num5;
	public JButton num6;
	public JButton num7;
	//public JButton num8;
	//public JButton num9;
	//public JButton num0;
	
	public JFrame frame;
	
	public TalkBoxConfig(){
		frame = new JFrame("TalkBox");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(1000, 500);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		num1 = new JButton("1");
		num2 = new JButton("2");
		num3 = new JButton("3");
		num4 = new JButton("4");
		num5 = new JButton("5");
		num6 = new JButton("6");
		num7 = new JButton("RECORD");
		//num8 = new JButton("8");
		//num9 = new JButton("9");
		//num0 = new JButton("0");
	
		num1.setBounds(0,100,150,150);
		num2.setBounds(150,100,150,150);
		num3.setBounds(300,100,150,150);
		num4.setBounds(450,100,150,150);
		num5.setBounds(600,100,150,150);
		num6.setBounds(750,100,150,150);
		num7.setBounds(300,300,250,100);
		//num8.setBounds(75,250,75,75);
		//num9.setBounds(150,250,75,75);
		//num0.setBounds(0,325,150,75);
		
		panel.add(num1);
		panel.add(num2);
		panel.add(num3);
		panel.add(num4);
		panel.add(num5);
		panel.add(num6);
		panel.add(num7);
		//panel.add(num8);
		//panel.add(num9);
		//panel.add(num0);
		
		frame.add(panel);
		
	}
}
