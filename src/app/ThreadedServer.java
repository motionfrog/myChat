package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ThreadedServer {

	private int listenport =8282;
	private ServerSocket serverSock;
	private Socket clientSock;
	
	public ThreadedServer() {
		Scanner sc = new Scanner(System.in);
		try {
			serverSock = new ServerSocket(listenport);
			System.out.println("Server is listening on " + listenport);
			
		}catch(IOException ex){
			System.out.println("IO Error when server started.");
			ex.printStackTrace();
		}
		
		while(true) {
			try {
				clientSock = serverSock.accept();
				
			}catch(IOException ex) {
				System.out.println("IO error when accepting client");
				ex.printStackTrace();
				return;
			}
			new ServerThread(clientSock).start();
			
		}
	}
	
	public static void main(String[] args) {
		new ThreadedServer();
	}
	
}
