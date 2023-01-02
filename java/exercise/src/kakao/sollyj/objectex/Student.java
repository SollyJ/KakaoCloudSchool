package kakao.sollyj.objectex;

public class Student {
    final String STATE = "";
    public static String schoolName;
    // static 초기화
    static {
        System.out.println("My Logo");
    }

    // 접근 지정자가 public이므로 외부에서 인스턴스를 통해 접근 가능
    protected int num;   // protected는 패키지내에선 그냥 public
    public String name;
    public int kor;
    public int eng;
    public int math;
}
