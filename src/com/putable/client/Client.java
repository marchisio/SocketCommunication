package com.putable.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client 
{
	private static int portNum = 8000;
	private Socket clientSocket;
	private BufferedReader reader;
	
	public Client(int newPortNum)
	{
		if(newPortNum > 3000 && newPortNum < 9000)
			portNum = newPortNum;
		
		init();
	}
	
	public void run()
	{
		try
		{
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			String outLine = null;
			String inLine = null;
			
			while((inLine = in.readLine()) != null)
			{
				System.out.println("Server: " + inLine);
				if(inLine.equals("Bye."))
					break;
				
				System.out.println("Client: ");
				
				outLine = reader.readLine();
				
				out.println(outLine);
			}
			
		}catch(Exception e)
		{
			System.out.println("Some error happens: " + e.getMessage());
		}
	}
	
	private void init()
	{
		try
		{
			clientSocket = new Socket("127.0.0.1", portNum);
		} catch (Exception e)
		{
			System.out.println("Failed to reserve port " + portNum);
			System.exit(-1);
		} 
		
		reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static void main(String[] args)
	{
		Client client = new Client(-1);
		client.run();
	}
}
