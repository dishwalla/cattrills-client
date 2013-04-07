package appl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	protected OutputStream out;
	protected InputStream in;

	public Client(OutputStream out, InputStream in){
		super();
		this.out = out;
		this.in = in;
	}

	public static void connect (){
		try {
			InetAddress addr = InetAddress.getByName("localhost");
			int port = 1234;
			Socket socket = new Socket(addr, port);
			PrintWriter wr = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);
			BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String inputLine = rd.readLine();
			System.out.println(inputLine);
			Scanner sc = new Scanner(System.in);
			String answer = sc.nextLine();
			wr.write(answer);
			wr.flush();
		} catch (UnknownHostException e) {
			System.out.println(e);
		} catch (IOException e) { 
			System.out.println(e);
		}
	}
	public void disconnect(){
		System.out.println("closing...");
	//	socket.close();

	}

	// public void sendMessage(IMessage msg){}

	//  void addReceiveListener(IReceiveListener listener);

	// void removeReceiveListener(IReceiveListener listener);


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		connect();
	}

}
