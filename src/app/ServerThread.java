package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerThread extends Thread{
	
	private Socket clientSocket;
	private BufferedReader clientInput;
	private InputStream inps;
	private ObjectInputStream objInput;
	private Sender user;
	
	public ServerThread(Socket s) {
		this.clientSocket = s;
	}
	
	public void run() {
		//Initialize io streams
		try {
			inps = clientSocket.getInputStream();
			objInput = new ObjectInputStream(inps);
			clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			try {
				user = (Sender)objInput.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println("Client Connected : " + user.getUsername());
			
			
		}catch(IOException ex){
			System.out.println("IO ERROR when initializing thread: " + ex.getMessage());
			return;
		}
		
		//Server input handler
		String msgInput;
		while(true) {
			try {
				msgInput = clientInput.readLine();
				if(msgInput == null || msgInput.equalsIgnoreCase("exitapp")) {
					System.out.println("Client leave " + user.getUsername());
					clientSocket.close();
					return;
				}else {
					System.out.println(user.getUsername() + ": " + msgInput);							
				}
			}catch(IOException e) {
				System.out.println("Client unexpectedly closed their connection: " + user.getUsername());
				break;
			}
		}
	}
	

}
