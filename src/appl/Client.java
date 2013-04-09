package appl;


import impl.CatTrillsClientServiceImpl;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Scanner;


public class Client {
	protected static InputStream in;
	protected static OutputStream os;
	static Scanner sc = new Scanner(System.in);
	protected String des;

	public static void main(String[] args) {
		try {
			CatTrillsClientServiceImpl ctcsi = new CatTrillsClientServiceImpl();
			ctcsi.connect();
			String response = ctcsi.getResponse();
			System.out.println(response);
			boolean res;
			do {
				String name = sc.nextLine();
				res = ctcsi.sendYourName(name);
			} while (res != true);
			//response = ctcsi.getResponse();
			//System.out.println(response);

			boolean continueGame = true;
			while(continueGame){
				System.out.println("The list of clients:");
				List<String> list = ctcsi.list();
				for(String key : list){
					System.out.println(key + "\t");
				}

				response = ctcsi.getResponse();
				System.out.println("choose partner");

				boolean res2;
				do {
					String partnerName = sc.nextLine();
					res2 = ctcsi.select(partnerName);
				} while (res2 != true);

				response = ctcsi.getResponse();
				System.out.println(response); //How many questions will be there in the game?

				String iter = sc.nextLine();
				ctcsi.putString(iter); //kol-vo voprosov
				int iterations = Integer.parseInt(iter);

				for(int i=0; i<iterations; i++){
					response = ctcsi.getResponse();
					System.out.println(response); //"write your quest"


					String quest = sc.nextLine();
					ctcsi.putString(quest); //zadaesh vopros
					if(i == iterations - 1) break;
					response = ctcsi.getResponse(); //poluchaesh vopros
					System.out.println(response); //vivodish vopros na ekran

					quest = sc.nextLine(); 
					ctcsi.putString(quest); // otvechaesh

				}
				System.out.println("\n");
				System.out.println("Result:" + "\n");
				String result = ctcsi.getEntireResult();

				System.out.println(result);
			
				System.out.println("Do you wanna quit?"); //"Do you wanna quit?"
				String yn = sc.nextLine();
				ctcsi.goOn(yn);
				if(yn.equals("y") || yn.equals("Y")){
					continueGame = false;
				}	
				response = ctcsi.getResponse();
			}
		}
		catch (Exception e) {
			System.out.println(e);
		} 
	}

	/*public static void main(String[] args) {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			int port = 1234;
			Socket serverSocket = new Socket(addr, port);

			ReaderThread rt = new ReaderThread(serverSocket.getInputStream());
			WriterThread wt = new WriterThread(serverSocket.getOutputStream());
			rt.start();
			wt.start();
		}

		catch (UnknownHostException e) {
			System.out.println(e);
		} catch (IOException e) { 
			System.out.println(e);}
	}*/



}


