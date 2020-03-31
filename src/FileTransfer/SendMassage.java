package FileTransfer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SendMassage implements Runnable {
	Socket client;
	Socket sendFileSocket;
	String userPath;

	SendMassage(Socket client, Socket sendFileSocket, String userPath) {
		this.client = client;
		this.sendFileSocket = sendFileSocket;
		this.userPath = userPath;
	}

	@Override
	public void run() {
		try {
			// 从标准输入缓冲区读取用户数据
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				String send = userInput.readLine();
				// 向服务器发送
				DataOutputStream output = new DataOutputStream(client.getOutputStream());
				output.writeUTF(send);

				if (send.length() > 6 && send.substring(0, 6).equals("[file]")) {
					String fileName = send.substring(6);
					new Thread(new SendFile(sendFileSocket, userPath + fileName)).start();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
