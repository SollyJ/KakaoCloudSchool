package kakao.sollyj.networkex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String[] args) {
		ServerSocket ss = null;
		Socket socket = null;

		try {
			// 서버 소켓 생성
			ss = new ServerSocket(9999);
			while (true) {
				System.out.println("서버 대기 중");
				socket = ss.accept();
				System.out.println("접속자 정보: " + socket);
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg = br.readLine();
				System.out.println("전송된 내용: " + msg);

				br.close();
				socket.close();
			}

		} catch (IOException e) {
			System.out.println("해당 포트 사용중!!!");
			try {
				ss.close();
			} catch (Exception ex) {
			}
		}
	}
}
