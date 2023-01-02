package kakao.sollyj.networkex;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class TCPClient {
	public static void main(String[] args) {
		InetAddress ia = null;
		Socket socket = null;
		PrintWriter pw = null;
		Scanner scan = new Scanner(System.in);

		try {
			while (true) {
				ia = InetAddress.getByName("192.168.0.19");
				socket = new Socket(ia, 9999);
				pw = new PrintWriter(socket.getOutputStream(), true);
				System.out.print("서버에게 보내는 문자열:");
				String msg = scan.nextLine();
				pw.println(msg + "\n");
				pw.flush();
				pw.close();
			}
		} catch (IOException e) {
			System.out.println("접속 오류 : " + e.toString());
		} finally {
			scan.close();
		}
	}
}
