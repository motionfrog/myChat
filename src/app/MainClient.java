package app;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {
	
	private Socket serverSock;
	private PrintStream clientOutput;
	private OutputStream outStream;
	private ObjectOutputStream objOutput;
	
	public static void main(String[] args) {	
		Scanner sc = new Scanner(System.in);
		System.out.print("Server: ");
		String address = sc.nextLine();
		MainClient client = new MainClient();
		client.runClient(address);
	}

	public void runClient(String address) {
		Scanner sc = new Scanner(System.in);
		String msg_inp = "";
		try {
			serverSock = new Socket(address,8282);
			
			clientOutput = new PrintStream(serverSock.getOutputStream());	
			outStream = serverSock.getOutputStream();
			objOutput = new ObjectOutputStream(outStream);
			//Create a test object
			System.out.print("Enter name: ");
			Sender sender = new Sender(sc.nextLine());
			
			System.out.println("Connected to server.");
			
			//Send the test object
			objOutput.writeObject(sender);
			
			while(!msg_inp.equalsIgnoreCase("exitapp")) {
				System.out.print(">");
				msg_inp = sc.nextLine();
				clientOutput.println(msg_inp);
				clientOutput.flush();	
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error Connecting to the server:" + e.getMessage());
		}finally {
			try {
				outStream.close();
				objOutput.close();
				serverSock.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


}
