package kakao.sollyj.streamex;

import java.io.File;
import java.util.Date;

public class FileInformationMain {
    public static void main(String[] args) {
        File f = new File("/Users/sollyj/Downloads/thread.txt");
        if(f.exists()) {
            System.out.println("파일 크기: " + f.length());

            Date fileDate = new Date(f.lastModified());
            System.out.println("마지막 수정 날짜: " + fileDate);
        } else {
            System.out.println("파일이 존재하지 않습니다.");
        }
    }
}
