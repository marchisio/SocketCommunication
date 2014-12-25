package com.putable.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * This file is the server side of socket communication. It will listen to 
 * communication and response to communication
 * 
 * @Author Lin Sun
 * @Email sun@unm.edu
 * */
public class Server 
{
	private static int portNum = 8000;
	private ServerSocket serverSocket;
	
	/*
	 * Constructor
	 * */
	public Server(int newPortNum)
	{
		if(newPortNum > 3000 && newPortNum < 9000)
			portNum = newPortNum;
		
		init();
	}
	
	/*
	 * Initialize the basic components in the class.
	 * */
	private void init()
	{
		try
		{
			serverSocket = new ServerSocket(portNum);
		}catch(Exception e)
		{
			System.out.println("Could not listent to port " + portNum + "!");
			System.exit(-1);
		}
	}
	
	/*
	 * Called to run the class.
	 * */
	private void run()
	{
		try
		{
			/*Initialize a client socket when there is a connection comming*/
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			String outLine = null;
			String inLine = null;
			KnockKnockProtocol kkp = new KnockKnockProtocol();
			
			outLine = kkp.processInput(null);
			out.println(outLine);
			
			while((inLine = in.readLine()) != null)
			{
				outLine = kkp.processInput(inLine);
				out.println(outLine);
				
				if(outLine.equals("Bye."))
					break;
			}
			
		}catch(Exception e)
		{
			System.out.println("Some error happens: " + e.getMessage());
		}
	}
	
	public static void main(String[] args)
	{
		Server server = new Server(-1);
		server.run();
	}
}
