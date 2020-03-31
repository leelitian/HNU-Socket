package EchoServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread {

	public static void main(String[] args) throws IOException {

		// 服务器侦听端口
		ServerSocket server = new ServerSocket(8888);
		System.out.println("--服务器启动--");

		while (true) {
			Socket connection = server.accept();
			Thread thread = new Thread(new Process(connection));
			thread.start();
			System.out.println("创建线程： " + thread.getName());
		}
	}
}