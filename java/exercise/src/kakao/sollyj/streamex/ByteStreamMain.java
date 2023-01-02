package kakao.sollyj.streamex;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class ByteStreamMain {
    public static void main(String[] args) {
        // 파일스트림은 예외처리가 강제
//        try(FileOutputStream fos = new FileOutputStream("./sample.bin", true)) {
//            String contents = "\nHello JAVA";
//            fos.write(contents.getBytes());
//            fos.flush();
//        } catch(Exception e) {
//            System.out.println(e.getLocalizedMessage());
//        }

//        try(FileInputStream fis = new FileInputStream("./sample.bin")) {
//            while(true) {
//                byte[] b = new byte[fis.available()];   // 읽을 수 있는 크기로 바이트 배열 생성
//                int len = fis.read(b);
//                if(len <= 0) {
//                    System.out.println("읽을 데이터 없음");
//                    break;
//                } else {
//                    // 숫자 배열 출력 - 텍스트가 아닌 경우
//                    System.out.println(Arrays.toString(b));
//                    // 문자열로 변환해서 출력 - 텍스트인 경우
//                    System.out.println(new String(b));
//                }
//            }
//        } catch(Exception e) {
//            System.out.println(e.getLocalizedMessage());
//        }

        /* PrintStream을 이용한 파일 입출력 */
        try(PrintStream ps = new PrintStream(new FileOutputStream("./sample.bin", true))) {
            String contents = "\nPrintStream을 이용한 파일 출력";
            ps.write(contents.getBytes());
            ps.print(contents);
            ps.flush();
        } catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream("./sample.bin"))) {
//            byte[] b = new byte[bis.available()];   // 파일에서 읽을 수 있는 크기로 바이트배열 정의
//            while(bis.read(b) > 0) {
//                System.out.println(Arrays.toString(b));
//                System.out.println(new String(b));
//            }

            // 나누어서 읽기 - 웹에서 파일 다운로드 받을 때 사용
            while(true) {
                byte[] b = new byte[16];   // 16바이트 단위로 읽어오기, 일반적으로는 128의 배수를 많이 이용
                int len = bis.read(b, 0, b.length);

                if(len <= 0)    break;

                // 받은 내용으로 작업
                // 문자열은 공백제거하고 출력
                // 파일은 크기만큼만 읽기
                System.out.println((new String(b)).trim());  // 공백제거하고 출력
            }
        } catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
