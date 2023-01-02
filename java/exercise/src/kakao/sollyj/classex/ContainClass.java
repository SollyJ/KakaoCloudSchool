package kakao.sollyj.classex;

public class ContainClass {
    public int x;
    public EmbedClass embed;
    ContainClass(){
        x = 10;
    }

    void createEmbed() {
        //생성자를 이용한 전달
        embed = new EmbedClass(this);
        //setter를 이용한 전달
        embed.setY(x);
    }

    void print() {
        System.out.println("나의 x 사용하기:" + x);
    }

    void method() {
        embed.disp();
    }

}
