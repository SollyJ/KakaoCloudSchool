package kakao.sollyj.java.lang;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Data me = new Data(1, "솔리", "쏠", 25);

//        Data me_copy = me;  // 인스턴스의 참조 복사(얕은복사)
//        System.out.println(me_copy);
//        me_copy.setAge(26);
//        System.out.println(me); // 원본의 값이 바뀐다.

//        Data me_copy = me.clone();   // clone을 이용한 복사
//        me_copy.setAge(27);
//        System.out.println(me_copy);
//        System.out.println(me);   // 원본의 값이 안바뀐다.
//
//        // ==과 equals
//        Data data1 = new Data(2, "보라돌이", "보라", 5);
//        Data data2 = new Data(2, "보라돌이", "보라", 5);
//        System.out.println(data1 == data2);   // ==는 해쉬코드를 비교하는 것
//        System.out.println(data1.equals(data2));   // equals는 내용은 비교하는 것


        int[] intArr = {60, 70, 20 ,30, 10};
        String[] stringArr = {"리사", "제니", "지수", "로제"};
        Arrays.sort(intArr);
        Arrays.sort(stringArr);

        System.out.println(Arrays.toString(intArr));
        System.out.println(Arrays.toString(stringArr));

        VO[] datas = new VO[4];
        datas[0] = new VO(1, "김민석");
        datas[1] = new VO(2, "박서준");
        datas[2] = new VO(3, "제니");
        datas[3] = new VO(4, "전지현");
    }
}
