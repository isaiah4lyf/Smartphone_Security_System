package sss.server;

import java.awt.TextArea;
import java.io.*;
import java.net.*;

import sss.clientHandler.Client;

public class Server {
	private ServerSocket	server;
	private boolean			running;
	private String URL;  
	private TextArea console_Like;
	public Server(int port,String URL,TextArea console_Like)
	{
		this.URL = URL;
		this.console_Like = console_Like;
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
		console_Like.append("Starting server for mobile \n");
		while (running)
		{
			try
			{
				Socket connectionToClient = server.accept();
				console_Like.append("New client \n");
				Client handler = new Client(connectionToClient,URL,console_Like);
				Thread clientThread = new Thread(handler);
				console_Like.append("Starting client thread \n");
				clientThread.start();
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}

}