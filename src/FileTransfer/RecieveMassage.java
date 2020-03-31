package FileTransfer;

import java.io.DataInputStream;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class RecieveMassage implements Runnable {
	ServerSocket server;

	RecieveMassage(ServerSocket server) {
		this.server = server;
	}

	@Override
	public void run() {
		try {
			// 监听端口
			Socket connection = server.accept();

			// 包装data输入输出流
			DataInputStream dataInputStream = new DataInputStream(connection.getInputStream());
			
			while (true) {
				// 获取客户发送过来的信息
				String clientSentence = dataInputStream.readUTF();
				// 获取时间
				Date date = new Date(); // this object contains the current date value
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				System.out.println("对方  " + formatter.format(date));
				System.out.println(clientSentence);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
