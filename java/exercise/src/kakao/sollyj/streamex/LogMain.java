package kakao.sollyj.streamex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;

public class LogMain {
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new FileReader("log.txt"))) {
			String[] ip = new String[1];	// ip별 접속횟수계산할 배열
			int[] trafficSum;    // 트래픽 합계 구할 변수
			HashMap<String, Integer> map = new HashMap<String, Integer>();

			while (true) {
				String log = br.readLine();

				if (log == null)
					break;

				String[] arr = log.split(" ");

				if(Arrays.stream(ip).anyMatch(s -> s.equals(arr[0]))) {

				}
				Arrays.copyOf(ip, ip.length + 1);
				ip[ip.length-1] = arr[0];
				//map.put(arr[0], Integer.parseInt(arr[arr.length-1]));   // (ip, traffic)형태로 map에 저장

				try {
					 //trafficSum += Integer.parseInt(arr[arr.length - 1]);
				} catch (Exception e) {
					// 정수로 변환해서 더하는데 예외가 발생하면(여기서는 -가 나오면) 그냥 넘어가도록
				}
			}

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
