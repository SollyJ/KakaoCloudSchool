package kakao.sollyj.java.lang;

public class VO {
    private int num;
    private String name;

    public VO() {
        super();
    }

    public VO(int num, String name) {
        this.num = num;
        this.name = name;
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

    @Override
    public String toString() {
        return "VO{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}
