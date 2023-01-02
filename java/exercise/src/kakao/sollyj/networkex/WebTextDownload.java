package kakao.sollyj.networkex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class WebTextDownload {
	public static void main(String[] args) {
		try {
			InetAddress ia = InetAddress.getByName("www.kakao.com");    // kakao.com의 주소정보 가져오기
			Socket socket = new Socket(ia, 80);    // 카카오에 연결 - 소켓생성

			// 요청을 전송하는 스트림을 생성
			PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
			pw.println("GET https://www.kakao.com");
			pw.flush();

			// 문자 단위로 전송을 받기 위한 스트림 생성
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			StringBuilder sb = new StringBuilder();
			while (true) {
				String imsi = br.readLine();
				if (imsi == null)
					break;
				sb.append(imsi + "\n");
			}
			String html = sb.toString();
			System.out.println(html);

			br.close();
			socket.close();

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
