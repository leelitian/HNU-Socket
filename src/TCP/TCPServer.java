package TCP;

import java.io.*;
import java.net.*;

class TCPServer {

	public static void main(String argv[]) throws Exception {

		// 建立端口
		ServerSocket server = new ServerSocket(8888);
		System.out.println("--服务器开启成功--");
		
		// 监听端口
		Socket connection = server.accept();
		System.out.println("--建立TCP连接--");
		
		// 包装data输入输出流
		DataInputStream inputStream = new DataInputStream(connection.getInputStream());
		DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
		
		while (true) {
			// 获取客户发送过来的信息
			String clientSentence = inputStream.readUTF();
			System.out.println("FROM CLIENT: " + clientSentence);

			// 向客户发送信息
			String send = "Hello, " + clientSentence;
			outputStream.writeUTF(send);
			outputStream.flush();
		}
	}
}
