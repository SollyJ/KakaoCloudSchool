package kakao.sollyj.classex;

public class SingletonMain {
    public static void main(String[] args) {
        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();

        System.out.println("obj1의 해시코드:" + obj1.hashCode());
        System.out.println("obj2의 해시코드:" + obj2.hashCode());   // 똑같다
    }
}
