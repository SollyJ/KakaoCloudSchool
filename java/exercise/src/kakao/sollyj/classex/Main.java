package kakao.sollyj.classex;

public class Main {
    public static void main(String[] args) {
        // static inner class 예제
        InstanceInner instanceInner = new InstanceInner();
        InstanceInner.Inner obj = new InstanceInner.Inner();

        // 인터페이스 예제
        // 1. 인터페이스를 Implement받은 클래스를 사용
        SampleAble interfaceInstance = new SampleAbleImpl();    // 인터페이스로 변수를 생성하고
        interfaceInstance.method();

        // 2. 익명클래스 사용
        SampleAble anonymous = new SampleAble() {
            @Override
            public void method() {
                System.out.println("anonymous 사용");
            }
        };
        anonymous.method();

        // 3. 변수를 만들지 않고 사용
        new SampleAble() {
            @Override
            public void method() {
                System.out.println("변수를 만들지 않고 사용");
            }
        }.method();
    }
}
