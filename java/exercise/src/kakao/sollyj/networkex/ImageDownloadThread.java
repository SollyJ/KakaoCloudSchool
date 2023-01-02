package kakao.sollyj.networkex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloadThread {
	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				try {
					// URL 생성
					String addr = "https://files.slack.com/files-pri/T04E0R22FQ9-F04GM474Z0B/kakaotalk_photo_2022-12-24-15-26-12.jpeg";

					// 파일확장자 추출
					String[] ar = addr.split("\\.");    // .은 \과 함께 기재해야한다.
					String ext = ar[ar.length - 1];
					System.out.println(ext);

					URL url = new URL(addr);
					// 연결 객체 생성
					HttpURLConnection con = (HttpURLConnection)url.openConnection();
					// 연결 옵션 설정
					con.setRequestMethod("GET");
					con.setConnectTimeout(30000);
					con.setUseCaches(false);

					// 읽어올 바이트 스트림 생성
					InputStream is = con.getInputStream();
					File f = new File("catmozzi." + ext);
					if (f.exists()) {
						System.out.println("이미 파일이 존재합니다.");
						return;
					}
					// 읽은 내용을 저장할 파일 스트림을 생성
					FileOutputStream fos = new FileOutputStream("catmozzi." + ext);

					while (true) {
						// 데이터를 저장할 바이트 배열 생성
						byte[] raster = new byte[512];
						int len = is.read(raster);
						if (len <= 0)
							break;
						fos.write(raster, 0, len);    // 읽은 내용은 파일에 기록
					}

					is.close();
					fos.close();
					con.disconnect();
				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
			}
		}.start();
	}
}
