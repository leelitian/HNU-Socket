package TCP;

import java.io.*;
import java.net.*;

class TCPClient {

	public static void main(String argv[]) throws Exception {

		// 建立到localhost:8888的连接
		Socket client = new Socket("localhost", 8888);
		System.out.println("--建立到服务器的连接--");

		// 从标准输入缓冲区读取用户数据
		BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			// 从键盘读入一行
			String send = userInput.readLine();
			if(send.equals("exit")) break;
			
			// 向服务器发送
			DataOutputStream output = new DataOutputStream(client.getOutputStream());
			output.writeUTF(send);
			output.flush();

			// 从服务器获取数据
			DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
			String serverSentence = dataInputStream.readUTF();
			System.out.println("FROM SERVER: " + serverSentence);
		}

		client.close();
	}
}
