package kakao.sollyj.regex;

public class Main {
    public static void main(String[] args) {
        // 스레드를 사용하지 않은경우
        new ThreadEx() {
            public void run() {
                for(int i=0; i<10; i++) {
                    try{
                        ThreadEx.sleep(1000);
                        System.out.println(i);
                    } catch(Exception e) {}
                }
            }
        }.run();

        new ThreadEx() {
            public void run() {
                for(int i=0; i<10; i++) {
                    try{
                        ThreadEx.sleep(1000);
                        System.out.println(i);
                    } catch(Exception e) {}
                }
            }
        }.start();
    }
}
