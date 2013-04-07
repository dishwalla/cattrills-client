package impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import service.CatTrillsClientService;

public class CatTrillsClientServiceImpl implements CatTrillsClientService{

	protected InputStream is;
	protected OutputStream os;
	PrintWriter wr;
	BufferedReader br; 
	protected Socket serverSocket;

	public CatTrillsClientServiceImpl() throws Exception{}

	public void connect() throws Exception{
		InetAddress addr = InetAddress.getLocalHost();
		//InetAddress addr = InetAddress.getByName("zukws0100");
		int port = 1234;
		this.serverSocket =	new Socket(addr, port);
		this.is = serverSocket.getInputStream();
		this.os = serverSocket.getOutputStream();
		wr = new PrintWriter(os, true);	
		br = new BufferedReader(new InputStreamReader(is));
	}

	@Override
	public boolean sendYourName(String name) throws Exception{
		putString(name);
		String response = getResponse();
		if (response.contains("already exists")){
			return false;
		}
		return true;
	}

	@Override
	public List<String> list() throws Exception{
		putString("list");
		String response = getResponse();
		String [] arr= response.split("\\s+");
		return Arrays.asList(arr);
	}

	@Override
	public boolean select(String name) throws Exception {
		putString("select");
		putString(name);
		String response = getResponse();
		if (response.contains("It's yours name") || response.contains("is busy") || response.contains("no such") || response.contains("Client rejected")){
			return false;
		}
		return true;
	}

	@Override
	public String getResponse() throws Exception {
		String response = br.readLine();
		return response;
	}

	@Override
	public String getEntireResult() throws IOException{
		String response = "";
		String justRead = br.readLine();
	/*	do {
		/*	byte [] arr = new byte[128];
			is.read(arr);	
			String justRead = br.
			if(justRead.contains("Write") || justRead.contains("Do you want")){
				break;
			}
			response = response.concat(justRead);
		} while (is.available() != 0);*/
		while(!(justRead.contains("Write") || justRead.contains("Do you want"))){
			response = response.concat(justRead + "\n");
			justRead = br.readLine();
		}
		return response;
		
	}

	@Override
	public void putString(String str) throws IOException{
		wr.write(str + "\n");
		wr.flush();
	}

	@Override //undone
	public boolean saveResult(String str){
		return true;
	}

	@Override//undone
	public void goOn(String yn) throws IOException{
		putString(yn);
	}
	
	@Override//undone
	public void standBy(){
	}

	@Override//undone
	public void acceptGameOffer(){}

	@Override//undone
	public void rejectGameOffer(){}



}
