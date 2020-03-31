package EchoServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

	private static Socket socket;

	public static void main(String[] args) throws UnknownHostException, IOException {

		// 获取当前日期，作为发送数据
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data = formatter.format(date);
		
		// 建立TCP连接并发送数据
		socket = new Socket("localhost", 8888);
		DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		output.writeUTF(data);

		// 关闭Socket
		socket.close();
	}
}