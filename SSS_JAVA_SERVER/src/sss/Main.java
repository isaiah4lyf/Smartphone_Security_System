package sss;

import javax.swing.JFrame;


import com.mathworks.engine.MatlabEngine;

import sss.gui.MainFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			MatlabEngine matEng1 = MatlabEngine.startMatlab();
			matEng1.eval("pi", null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
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
