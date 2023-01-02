package kakao.sollyj.inheritanceex;

public class SuperClass {
    private int num;    // 상속은 되지만 하위클래스에서 접근이 안된다.
    protected String name;

    public SuperClass() { }

    public SuperClass(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void display() {
    }
}
