package appl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ReaderThread extends Thread{

	protected InputStream in;

	public ReaderThread(InputStream in){
		this.in = in;
	}

	public void run(){
		//System.out.println("RT started");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String inputLine;
			while((inputLine = br.readLine()) != null) {
				System.out.println(inputLine);
			}
		} 
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
