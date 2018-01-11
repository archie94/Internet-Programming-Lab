import java.io.*;
import java.net.*;
import javax.swing.*;

class Client {
	public static void main(String args[])throws IOException {
		Socket socket = new Socket("localhost", 7777);
		String msg = JOptionPane.showInputDialog("Enter msg to client");

		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		dos.writeUTF(msg);
		dos.flush();

		msg = (String)dis.readUTF();
		JOptionPane.showMessageDialog(null, msg);

		dos.close();
		dis.close();
		socket.close();
	}
}