package sss.clientHandler;

import java.awt.TextArea;
import java.io.*;
import java.net.*;

import com.mathworks.engine.MatlabEngine;

import sss.matlabManager.MatEngineManager;

public class Client implements Runnable{
	private Socket connectionToClient;
	private DataInputStream in;
	private DataOutputStream out;
	private String URL;
	private TextArea consoleLike;
	private String dataAbsolutePath;
	private MatEngineManager MatEngines;
	public Client(Socket socketConnectionToClient,String URL,TextArea console_Like,MatEngineManager MatEngines)
	{
		this.connectionToClient = socketConnectionToClient;
		this.URL = URL;
		this.consoleLike = console_Like;
		this.MatEngines = MatEngines;
		try
		{
			in = new DataInputStream(connectionToClient.getInputStream());
			out = new DataOutputStream(connectionToClient.getOutputStream());
			dataAbsolutePath  = new File(".").getCanonicalPath() + "/data";

			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		consoleLike.append("Processing client commands \n");
		boolean processing = true;
		try
		{
			while (processing)
			{
				String command = in.readUTF();
				consoleLike.append(command + "\n");
				try 
				{
					switch (command)
					{
						case "REG":
							processing = false;		
							consoleLike.append(dataAbsolutePath + "\n");
							consoleLike.append(URL + "\n");
							
							break;
						case "LOGIN":
							processing = false;
							MatlabEngine matEng = getMAT();
							matEng.eval("p = pi;", null, null);
							consoleLike.append(matEng.getVariable("p") + "\n");
							double pi = matEng.getVariable("p");
							sendMessage(String.valueOf(pi));
							close();
							MatEngines.ReleaseMATEngine( matEng.getVariable("MAT"));
							consoleLike.append("Releasing MATLAB Enigne " + matEng.getVariable("MAT") + ".....\n");
							break;
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	private final int readFully(DataInputStream in, byte b[], int off, int len) throws IOException {
	    if (len < 0)
	        throw new IndexOutOfBoundsException();
	    int n = 0;
	    while (n < len) {
	        int count = in.read(b, off + n, len - n);
	        if (count < 0)
	            break;
	        n += count;
	    }
	    return n;
	}  
	
	*/
	private MatlabEngine getMAT()
	{
		MatlabEngine matEng = null;
		boolean Found = false;

		while(Found == false)
		{
			try
			{
				matEng = MatEngines.GetFreeMATEngi();
				Found = true;
				consoleLike.append("Found MATLAB Enigne " + matEng.getVariable("MAT") + ".....\n");
			}
			catch(Exception ex)
			{
				Found = false;

			}
		}
		return matEng;
	}
	private void close()
	{
		try
		{
			connectionToClient.close();
			out.close();
			in.close();
			consoleLike.append("Closing Client Connection.... \n");
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	private void sendMessage(String message)
	{
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	
}