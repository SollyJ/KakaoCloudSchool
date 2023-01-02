package kakao.sollyj.inheritanceex;

public class Main {
    public static void main(String[] args) {
//        SuperClass sup = new SuperClass(1, "리리솔리");
//        SubClass sub = new SubClass(2, "쏠라씨", "쏠라씨", 25);
//        System.out.println(sup.getNum() + sup.getName());
//        System.out.println(sub.getNum() + sub.getName() + sub.getNickname() + sub.getAge());

        SuperClass sup1 = new SuperClass();
        SuperClass sup2 = new SuperClass();
        SubClass sub1 = new SubClass();
        SubClass sub2 = new SubClass();
        // 선언할 때는 SuperClass인데 인스턴스는 SubClass로 생성
        // 호출할 수 있는 것은 SuperClass 중에 있지만 호출되는 것은 SubClass의 것
        SuperClass sup3 = new SubClass();
        SubClass sub3 = (SubClass)sup3;
    }

}
