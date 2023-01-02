package kakao.sollyj.streamex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringStreamMain {
    public static void main(String[] args) {
        String directory = "/Users/sollyj/IdeaProjects/exercise/";

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String filename = sdf.format(date);   // 파일 이름 만들기

        String path = String.format("%s%s%s", directory, filename, ".log");   // 파일 경로 만들기

        // 특정한 디렉토리에 날짜별로 파일을 만들어서 log를 기록 - logging하는 방법
//        try(PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
//            pw.println("hi");
//            pw.println("how r u");
//            pw.println("good");
//            pw.flush();
//        } catch(Exception e) {
//            System.out.println(e.getLocalizedMessage());
//        }

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            while(true) {
                String contents = br.readLine();
                if(contents == null)    break;
                System.out.println(contents);
            }
        } catch(Exception e) {
            System.out.println(e.getLocalizedMessage());   // 예외 이름 알려줌
            e.printStackTrace();    // 예외 발생 경로 차례대로 알려줌
        }
    }
}
