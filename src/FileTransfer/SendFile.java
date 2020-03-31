package FileTransfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SendFile implements Runnable {

	Socket client;
	String filePath;

	public SendFile(Socket client, String filePath) {
		this.client = client;
		this.filePath = filePath;
	}

	@Override
	public void run() {
		try {
			OutputStream outputStream = client.getOutputStream();
			// 创建文件对象
			File file = new File(filePath);
			// 获取文件名称，为上传文件设置名称
			String name = file.getName();

			// 写入文件名称长度
			outputStream.write(name.getBytes().length / 256);
			outputStream.write(name.getBytes().length % 256 - 128);

			// 写入文件名称
			outputStream.write(name.getBytes());

			// 写入内容
			byte[] b = new byte[1024]; // 设置缓冲区
			int len; // 设置内容长度

			// 创建字节输出流，读取内容将它输出到outputStream对象中
			FileInputStream fileInputStream = new FileInputStream(file);
			while ((len = fileInputStream.read(b)) != -1) {
				outputStream.write(b, 0, len);
			}

			System.out.println("系统消息：" + name + "发送成功！");

			// 告知服务器输入完毕
			client.shutdownInput();
			// 关流
			fileInputStream.close();
			outputStream.close();
			client.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
