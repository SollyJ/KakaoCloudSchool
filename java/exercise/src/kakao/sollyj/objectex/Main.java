package kakao.sollyj.objectex;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student();
        Student student2 = new Student();
        student1.num = 1;
        student1.name = "리리";
        student1.kor = 88;
        student1.eng = 98;
        student1.math = 100;

        student1.schoolName = "미림여자고등학교";    // static속성에 접근
        student2.schoolName = "삼성고등학교";
        System.out.println(student1.schoolName);    // static변수는 인스턴스끼리 공유하기 때문에 동일한 데이터가 출력된다.
        System.out.println(student2.schoolName);
    }
}
