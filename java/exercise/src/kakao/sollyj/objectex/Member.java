package kakao.sollyj.objectex;

import java.util.Date;

public class Member {
    private String email;
    private String name;
    private String[] nickames;
    private Date birthday;
    private boolean married;
    private int age;

    public void email(String email) {
        this.email = email;
    }
    public void setNickames(String[] nickames) {
        this.nickames = nickames;
    }
    // 배열이나 List와 같은 컬렉션이 있을 때는 한개 데이터만 읽고 쓸수있는 메서드를 제공해주는 것이 좋다.
    public String getNickname(int index) {
        return nickames[index];
    }
    public void setNickname(int index, String nickname) {
        this.nickames[index] = nickname;
    }
    public void birthday(Date birthday) {
        this.birthday = birthday;
    }
    public void married(boolean married) {
        this.married = married;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }

    // 모든 속성의 toString을 호출해서 하나의 문자열로 만들어주는 메서드
    // 디버깅을 위해서 호출하는 메서드
    public String toString() {
        return "Member [email";
    }
}
