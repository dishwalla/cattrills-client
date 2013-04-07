package appl;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class WriterThread extends Thread{

	protected OutputStream os;

	public WriterThread(OutputStream os){
		this.os = os;
	}

	public void run(){
		//System.out.println("WT started");

		try {

			PrintWriter wr = new PrintWriter(os, true);	
			Scanner sc = new Scanner(System.in);
			while(true){
				String answer = sc.nextLine(); 
				wr.println(answer);
			}
		} 
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}