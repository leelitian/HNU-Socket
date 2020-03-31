package UDP;

import java.io.*;
import java.net.*;

class UDPClient {
	public static void main(String args[]) throws Exception {

		// 创建Socket，并获取服务器IP
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");

		// 从标准输入缓冲区读取数据
		BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String sentence = inputBuffer.readLine();

			// 封装UDP包
			byte[] sendData = new byte[1024];
			sendData = sentence.getBytes();

			// 发送包到目的IP的8888端口
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8888);
			clientSocket.send(sendPacket);

			// 获取服务器发来的包
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);

			String recieveSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());

			System.out.println("FROM SERVER:" + recieveSentence);
		}

//		clientSocket.close();
	}
}
