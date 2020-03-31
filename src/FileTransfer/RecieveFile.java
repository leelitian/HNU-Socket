package FileTransfer;

import java.io.File;
import java.net.ServerSocket;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class RecieveFile implements Runnable {

	ServerSocket server;
	String folderPath;

	public RecieveFile(ServerSocket server, String folderPath) {
		this.server = server;
		this.folderPath = folderPath;
	}

	@Override
	public void run() {
		try {
			Socket socket = server.accept();

			// 获取字节输入流
			InputStream inputStream = socket.getInputStream();

			// 获取名称长度
			byte len1 = (byte) inputStream.read();
			byte len2 = (byte) inputStream.read();

			// 根据名称设置的规则，获取名称的长度
			int fileLength = len1 * 256 + len2 + 128;

			// 设置缓冲区
			byte[] data = new byte[fileLength];
			// 获取文件名称
			inputStream.read(data);
			String fileName = new String(data, 0, fileLength);
			
			// 创建字节输出流
			// 并在指定位置，创建文件
			FileOutputStream fileOutputStream = new FileOutputStream(
					new File(folderPath + new String(data, 0, fileLength)));

			// 写入内容
			while ((fileLength = inputStream.read(data)) != -1) {
				fileOutputStream.write(data, 0, fileLength);
			}
			
			// 告知客户端接收完毕
			socket.shutdownInput();
			
			// 关流
			fileOutputStream.close();
			inputStream.close();
			socket.close();

			server.close();
			System.out.println("系统消息：成功接收文件" + fileName);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
