package sss.gui;
import java.awt.*;
import javax.swing.*;

import sss.server.Server;

import java.io.*;
public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextArea console_Like;
	private String dataAbsolutePath;
	public MainFrame()
	{
		try 
		{
			dataAbsolutePath  = new File(".").getCanonicalPath() + "/data";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout(new GridLayout(1,1));
		console_Like = new TextArea();
		console_Like.setEditable(false);
		add(console_Like);
		
		//Start Server Thread
		new Thread(new Runnable() {
            @Override
            public void run() {
                try {
            		try 
            		{
            			new Server(8060,"ccs",console_Like);
            		} catch (Exception e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
                }
                catch (Exception ex)
                {
                }
            }
        }).start();
        
		
		console_Like.append(dataAbsolutePath + "\n");
	}

}
