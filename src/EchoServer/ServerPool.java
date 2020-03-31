package EchoServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPool {

	static int serverPort = 8888; // 服务器侦听的端口号
	static int poolSize = 5; // 自定义线程池的大小

	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(serverPort);

		System.out.println("--服务器开启--");

		// 每个线程都反复循环，从（共享的）ServerSocket实例接收客户端连接。
		for (int i = 0; i < poolSize; i++) {
			Thread thread = new Thread() {
				@Override
				public void run() {
					while (true) {
						try {
							Socket socket = server.accept();
							Process.handleClient(socket);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			};

			thread.start();
			System.out.println("线程 " + thread.getName() + " 开始工作 ");
		}
	}

}