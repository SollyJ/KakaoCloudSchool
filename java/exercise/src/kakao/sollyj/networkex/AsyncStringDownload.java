package kakao.sollyj.networkex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncStringDownload {
	public static void main(String[] args) {
		// 스레드를 만들어서 다운로드
		// 비동기 방식
		new Thread() {
			@Override
			public void run() {
				try {
					// URL 생성
					URL url = new URL("https://www.kakao.com");

					// 연결 객체 생성
					HttpURLConnection con = (HttpURLConnection)url.openConnection();

					// 연결 옵션 설정
					con.setConnectTimeout(10000);
					con.setUseCaches(false);
					con.setRequestMethod("GET");

					// 읽기 위한 스트림 생성
					BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
					StringBuilder sb = new StringBuilder();

					while (true) {
						String line = br.readLine();

						if (line == null) {
							break;
						}
						sb.append(line + "\n");
					}

					String html = sb.toString();
					System.out.println(html);

					br.close();
					con.disconnect();
				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
			}
		}.start();
	}
}
