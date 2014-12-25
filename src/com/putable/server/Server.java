package com.putable.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
	private static int portNum = 8000;
	
	public Server(String[] args)
	{
		if(args.length > 0)
		{
			int newPortNum = Integer.parseInt(args[0]);
			
			if(newPortNum > 3000 && newPortNum < 9000)
				portNum = newPortNum;
		}
		
		ServerSocket serverSocket = null;
		
		try
		{
			serverSocket = new ServerSocket(portNum);
		}catch(Exception e)
		{
			System.out.println("Could not listent to port " + portNum + "!");
			System.exit(-1);
		}
		
		
		try
		{
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}catch(Exception e)
		{
			
		}
	}
}
