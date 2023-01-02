package kakao.sollyj.openapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class KakaoBookSearch {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// url 만들기
					String address = "https://dapi.kakao.com/v3/search/book?target=title&query=";
					// GET방식에서 파라미터는 반드시 인코딩되어야 한다.
					address += URLEncoder.encode("자바", "utf-8");

					// connection 만들기
					URL url = new URL(address);
					HttpURLConnection connection = (HttpURLConnection)url.openConnection();
					connection.setConnectTimeout(30000);
					connection.setUseCaches(false);
					connection.setRequestMethod("GET");
					// 키 설정
					connection.setRequestProperty("Authorization", "KakaoAK 1e36bb19fbde4e5940ab92b58d4a4a1e");
					// 데이터 읽어오기
					BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					StringBuilder sb = new StringBuilder();
					while (true) {
						String imsi = br.readLine();
						if (imsi == null)
							break;
						sb.append(imsi + "\r\n");
					}
					String result = sb.toString();
					System.out.println(result);

					br.close();
					connection.disconnect();
				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
			}
		}).start();
	}
}
