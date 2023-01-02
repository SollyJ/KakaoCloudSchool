package kakao.sollyj.java.lang;

import java.util.Objects;

// 하나의 데이터 묶음을 표현하기 위한 클래스
// VO(Value Object)
public class Data implements Cloneable{
    private int num;
    private String name;
    private String nickname;
    private int age;

    // 디폴트 생성자
    public Data() {
        super();
    }

    // 속성을 대입받아서 생성하는 생성자
    public Data(int num, String name, String nickname, int age) {
        this.num = num;
        this.name = name;
        this.nickname = nickname;
        this.age = age;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // 인스턴스의 내용을 빠르게 만들기 위해서 사용
    // 디버깅을 위한 메서드
    @Override
    public String toString() {
        return "Data [num = " + num + ", name = " + name + ", nickname = " + nickname + ", age = " + age + "]";
    }

    public Data clone() {   // clone() 재정의
        Data data = new Data();
        data.num = this.num;
        data.name = this.name;
        data.nickname = this.nickname;
        data.age = this.age;

        return data;
    }

    @Override
    public boolean equals(Object obj) {   // equals() 재정의
//        // 첫번째 방법
//        boolean result = false;
//        Data compare = (Data)obj;
//        if(this.num == compare.getNum() && this.name == compare.getName())  return true;
//        return result;

        // 두번째 방법 (속도 더 빠름)
        // Objects.hash(데이터 나열)
        // 데이터들을 가지고 정수로 만든 해쉬코드 생성
        Data compare = (Data)obj;
        return Objects.hash(this.num, this.name) == Objects.hash(compare.num, compare.name);
    }
}
