package UDP;

import java.net.*;

class UDPServer {

	public static void main(String args[]) throws Exception {

		// 开放一个端口
		DatagramSocket serverSocket = new DatagramSocket(8888);
		System.out.println("--服务器准备接收Packet--");

		// 准备容器接收
		byte[] container = new byte[1024];
		// 等待包裹容器封包
		DatagramPacket receivePacket = new DatagramPacket(container, container.length);

		byte[] sendData = new byte[1024];

		while (true) {
			// 接收包裹
			serverSocket.receive(receivePacket);
			String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
			System.out.println("FROM CLIENT: " + clientMessage);

			// 从UDP包中获取IP，端口号
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();

			// 将要发送的字符串
			String send = "hello, " + clientMessage;

			// 将字符串转化为字节流
			sendData = send.getBytes();

			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
		}
	}
}