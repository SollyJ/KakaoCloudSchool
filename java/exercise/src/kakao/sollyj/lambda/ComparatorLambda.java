package kakao.sollyj.lambda;

import java.util.Arrays;

public class ComparatorLambda {
	public static void main(String[] args) {
		String[] arr = {"엽떡", "마라탕", "마카롱", "허니콤보", "아메리카노"};

		Arrays.sort(arr, (o1, o2) -> o2.compareTo(o1));    // 내림차순

		System.out.println(Arrays.toString(arr));
	}
}
