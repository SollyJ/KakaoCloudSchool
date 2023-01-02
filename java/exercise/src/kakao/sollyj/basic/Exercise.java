package kakao.sollyj.basic;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Exercise {
    public static void main(String[] args) {
//        int x = 100;
//        int y = 100;
//
//        System.out.println(System.identityHashCode(x));
//        System.out.println(System.identityHashCode(y));   // 똑같은 해쉬코드값이 나온다.
//
//
//        int n1 = 20;
//        int n2 = -20;
//        // 정수 데이터가 2진수로 변환된 것을 출력
//        System.out.println("n1: " + Integer.toBinaryString(n1));
//        System.out.println("n2: " + Integer.toBinaryString(n2));
//        // 1의 보수 구하기
//        System.out.println("n2의 1의보수: " + Integer.toBinaryString(~n2));
//
//
//        System.out.println(10 < 10L);   // 정수끼리는 자료형이 달라도 비교 가능
//        System.out.println((1.0 - 0.8) < 0.2);   // 실수는 크기 비교할때 정확한 결과가 안나올수있음
//
//
//        String s1 = "JAVA";
//        String s2 = "JAVA"; // Literal로 생성하면 동일한 데이터 == 동일한 해쉬코드
//        System.out.println((s1 == s2));
//
//        Scanner scan = new Scanner(System.in);
//        System.out.println("s3을 입력하세요: ");
//        String s3 = scan.nextLine();    // JAVA를 입력
//        System.out.println((s1 == s3)); // 똑같은 JAVA를 입력해도 해쉬코드가 달라서 false
//        System.out.println(s1.equals(s3));  // equals로 비교해야 true가 나온다
//
//
//        try (
//                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        ) {
//            System.out.print("이름을 입력하세요:");
//            //한 줄의 문자열 입력받기
//            String str = br.readLine();
//            bw.write("이름: " + str);
//            bw.newLine();   // 개행
//
//            System.out.print("나이를 입력하세요:");
//            String age = br.readLine();
//            int nai = Integer.parseInt(age);    // age라는 문자열을 정수로 변환
//            bw.write("나이: " + nai + "\n");
//
//            br.close();   // 입력을 다 받았으면 close
//            bw.flush();   // 남은값 출력, 버퍼 초기화
//            bw.close();   // 출력 다 했으니 close
//        } catch (Exception e) {  // 예능외처리 필수
//            System.out.println(e.getLocalizedMessage());
//        }


        int[] arr = {1, 2, 3, 4, 5};
        int[] copy_arr;
        copy_arr = Arrays.copyOf(arr, arr.length + 5);
        for(int i=5; i<copy_arr.length; i++) {
            copy_arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(copy_arr));
    }
}
