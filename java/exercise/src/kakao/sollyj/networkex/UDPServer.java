package kakao.sollyj.networkex;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
	public static void main(String[] args) {
		try (DatagramSocket dsoc = new DatagramSocket(8888);) {   // UDP 소켓 생성
			byte[] data = new byte[65536];

			// 데이터를 전송받을 패킷 클래스의 인스턴스 생성
			DatagramPacket dp = new DatagramPacket(data, data.length);
			System.out.println("서버 서비스 시작...");
			while (true) {
				dsoc.receive(dp);    // 데이터 받기
				System.out.println("------보낸 곳 주소 : " + dp.getAddress().getHostAddress());    // 클라이언트 정보 확인
				System.out.println("자료 크기 : " + dp.getLength());
				String message = new String(dp.getData()).trim();
				System.out.println("내용 : " + message);    // 받은 메시지 출력
			}
		} catch (Exception e) {
			System.out.println("오류 : " + e);
		}
	}
}
