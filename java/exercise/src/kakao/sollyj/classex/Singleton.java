package kakao.sollyj.classex;

class Singleton {
    static Singleton obj = null;
    private Singleton(){   // 생성자를 private로 선언
    }
    public static Singleton getInstance(){
        if(obj == null)
            obj = new Singleton();
        return obj;
    }

}
