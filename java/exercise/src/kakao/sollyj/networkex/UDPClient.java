package kakao.sollyj.networkex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
	public static void main(String[] args) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("보낼 자료 입력 : ");
			String msg = in.readLine();

			DatagramSocket dsoc = new DatagramSocket();

			InetAddress ia = InetAddress.getByName("192.168.0.19");
			DatagramPacket dp = new DatagramPacket(msg.getBytes(),
				msg.getBytes().length, ia, 8888);
			dsoc.send(dp);
			System.out.println("전송완료");
			dsoc.close();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
