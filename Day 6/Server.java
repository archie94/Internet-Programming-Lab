import java.io.*;
import java.net.*;

class Server {
	public static void main(String args[]) {

		try {

			/*
			ServerSocket : This class implements server sockets. 
			A server socket waits for requests to come in over 
			the network. It performs some operation based on that 
			request, and then possibly returns a result to the 
			requester.
			*/
			ServerSocket serverSocket = new ServerSocket(7777); // bind to port 7777
			System.out.println("Server running on 0.0.0.0 on port 7777 ...");

			while(true) {
				/*
				A socket is one endpoint of a two-way communication link 
				between two programs running on the network. A socket is 
				bound to a port number so that the TCP layer can identify 
				the application that data is destined to be sent to.
				*/
				Socket clientSocket = serverSocket.accept(); // establish connection
				DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
				DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
				String msg = (String)dis.readUTF();

				System.out.println("Client says : " + msg);

				try {
					int num = Integer.parseInt(msg);
					if(num < 0) {
						throw new Exception();
					}

					num *= 2;

					msg = "Double the value : " + num;
					dos.writeUTF(msg);
					dos.flush();
				} catch(Exception e) {
					msg = "Enter only a proper number";
					dos.writeUTF(msg);
					dos.flush();
				}
			}

			//serverSocket.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}