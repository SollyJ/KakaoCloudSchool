package kakao.sollyj.inheritanceex;

public class SubClass extends SuperClass{
    private String nickname;
    private int age;

    public SubClass() { }

    public SubClass(int num, String name, String nick, int age) {
        super(num, name);

        this.nickname = nick;
        this.age = age;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public int getAge() {
        return age;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("하위클래스의 메서드");
    }
}
