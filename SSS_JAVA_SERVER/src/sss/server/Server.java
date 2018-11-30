package sss.server;

import java.awt.TextArea;
import java.io.*;
import java.net.*;
import sss.clientHandler.Client;
import sss.matlabManager.MatEngineManager;

public class Server {
	private ServerSocket server;
	private boolean	running;
	private String URL;  
	private TextArea consoleLike;
	private MatEngineManager MatEngines;
	public Server(int port,MatEngineManager MatEngines,String URL,TextArea console_Like)
	{
		this.URL = URL;
		this.consoleLike = console_Like;
		this.MatEngines = MatEngines;
		try
		{
			console_Like.append("Creating server for mobile \n");
			server = new ServerSocket(port);
			running = true;
			start();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	public void start()
	{
		consoleLike.append("Starting server for mobile \n");
		while (running)
		{
			try
			{
				Socket connectionToClient = server.accept();
				consoleLike.append("New client \n");
				Client handler = new Client(connectionToClient,URL,consoleLike,MatEngines);
				Thread clientThread = new Thread(handler);
				consoleLike.append("Starting client thread \n");
				clientThread.start();
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}

}