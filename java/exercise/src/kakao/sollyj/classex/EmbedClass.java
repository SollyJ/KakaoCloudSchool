package kakao.sollyj.classex;

public class EmbedClass {

    private ContainClass parent;
    private int y;

    EmbedClass(ContainClass parent){
        this.parent = parent;
    }

    public void setY(int y) {
        this.y = y;
    }

    void disp() {
        System.out.println("부모의 x 사용하기:" + parent.x);
        parent.x = 20;
        System.out.println("y:" + y);
    }
}
