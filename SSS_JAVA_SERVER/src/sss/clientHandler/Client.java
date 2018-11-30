package sss.clientHandler;

import java.awt.TextArea;
import java.io.*;
import java.net.*;

public class Client implements Runnable{
	private Socket connectionToClient;
	private DataInputStream in;
	private DataOutputStream out;
	private String URL;
	private TextArea console_Like;
	private String dataAbsolutePath;
	public Client(Socket socketConnectionToClient,String URL,TextArea console_Like)
	{
		this.connectionToClient = socketConnectionToClient;
		this.URL = URL;
		this.console_Like = console_Like;
		try
		{
			in = new DataInputStream(connectionToClient.getInputStream());
			out = new DataOutputStream(connectionToClient.getOutputStream());
			dataAbsolutePath  = new File(".").getCanonicalPath() + "/data";
			
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		console_Like.append("Processing client commands \n");
		boolean processing = true;
		try
		{
			while (processing)
			{
				String command = in.readUTF();
				console_Like.append(command + "\n");
				try 
				{
					switch (command)
					{
						case "REG":
							processing = false;		
							console_Like.append(dataAbsolutePath + "\n");
							console_Like.append(URL + "\n");
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
	
	
	private void close()
	{
		try
		{
			connectionToClient.close();
			out.close();
			in.close();
			console_Like.append("Closing Client Connection.... \n");
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
	*/
	
}