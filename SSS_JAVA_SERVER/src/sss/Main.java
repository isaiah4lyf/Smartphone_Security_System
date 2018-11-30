package sss;

import javax.swing.JFrame;
import sss.gui.MainFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame frame = new MainFrame();
		frame.pack();
		frame.setTitle("SmartPhone Security System");
		frame.setSize(800,400);
		frame.setResizable(false);
		frame.setLocation(250,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
