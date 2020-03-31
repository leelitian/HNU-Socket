package EchoServer;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Process implements Runnable {

	private Socket client;

	public Process(Socket socket) {
		this.client = socket;
	}

	public static void handleClient(Socket client) throws IOException {

		// 包装data输入输出流
		DataInputStream inputStream = new DataInputStream(client.getInputStream());

		// 获取客户发送过来的信息
		String message = inputStream.readUTF();
		System.out.println("FROM CLIENT: " + message);

		// 关闭资源
		inputStream.close();
		client.close();
	}

	@Override
	public void run() {
		try {
			handleClient(client);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}