package FileTransfer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UserTwo {
	
	static String userTwoPath = System.getProperty("user.dir") + "\\UserTwo\\";
	
	public static void main(String[] args) throws IOException, InterruptedException {

		// 启动接收消息线程
		ServerSocket recieveMessageSocket = new ServerSocket(8602);
		new Thread(new RecieveMassage(recieveMessageSocket)).start();

		// 启动文件接收线程
		ServerSocket recieveFileSocket = new ServerSocket(9602);
		new Thread(new RecieveFile(recieveFileSocket, userTwoPath)).start();
		
		// 等待对方就绪
		Thread.sleep(3000);

		// 启动发送消息线程
		Socket sendMessageSocket = new Socket("localhost", 8601);
		Socket sendFileSocket = new Socket("localhost", 9601);
		new Thread(new SendMassage(sendMessageSocket, sendFileSocket, userTwoPath)).start();
	}
}