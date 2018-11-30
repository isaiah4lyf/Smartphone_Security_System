package sss.gui;
import java.awt.*;
import javax.swing.*;

import sss.matlabManager.MatEngineManager;
import sss.server.Server;

import java.io.*;
public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextArea consoleLike;
	private String dataAbsolutePath;
	private MatEngineManager MatEngines;
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
		consoleLike = new TextArea();
		consoleLike.setEditable(false);
		add(consoleLike);
		
		//Starting MATLAB Engines
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				MatEngines = new MatEngineManager(consoleLike);
    			MatEngines.setMat1InUse(true);
    			MatEngines.setMat2InUse(true);
    			MatEngines.setMat3InUse(true);
    			MatEngines.setMat4InUse(true);  
				MatEngines.StartMATEngines();
			}
			
		}).start();
		
		//Start Server Thread
		new Thread(new Runnable() {
            @Override
            public void run() {
                try {
            		try 
            		{
            			while(MatEngines.isMat1InUse() == true || MatEngines.isMat2InUse() == true || MatEngines.isMat3InUse() == true || MatEngines.isMat4InUse() == true){
            				consoleLike.append("Initializing MATLAB Engines.......\n");
            				Thread.sleep(10000);
            			};
            			new Server(8060,MatEngines,"ccs",consoleLike);
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
        

		
		consoleLike.append(dataAbsolutePath + "\n");
	}

}
