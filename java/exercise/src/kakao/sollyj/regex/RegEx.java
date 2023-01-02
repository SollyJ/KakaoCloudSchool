package kakao.sollyj.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {
    public static void main(String[] args) {
        String[] ar = {"abc", "asc", "aiic"};
        // a로 시작하고 아무글자 하나 있고 c로 끝나는 정규표현식
        Pattern p = Pattern.compile("^a.c$");
        for(String str : ar) {
            Matcher matcher = p.matcher(str);   // 일치하는지 조사
            System.out.println(str + ":" + matcher.find());
        }
    }
}
