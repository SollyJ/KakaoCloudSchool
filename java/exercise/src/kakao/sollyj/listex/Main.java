package kakao.sollyj.listex;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        /* ArrayList와 LinkedList 시간 비교
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        for(int i=0; i<10000; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long start;
        long end;

        // ArrayList 조회
        start = System.currentTimeMillis();
        for(int i=0; i<10000; i++) {
            Integer integer = arrayList.get(i);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList 조회 시간: " + (end - start));

        // ArrayList 삽입
        start = System.currentTimeMillis();
        for(int i=0; i<10000; i++) {
            arrayList.add(1, 2);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList 중간에 추가하는 시간: " + (end - start));

        // LinkedList 조회
        start = System.currentTimeMillis();
        for(int i=0; i<10000; i++) {
            Integer integer = linkedList.get(i);
        }
        end = System.currentTimeMillis();
        System.out.println("LinkedList 조회 시간: " + (end - start));

        // LinkedList 삽입
        start = System.currentTimeMillis();
        for(int i=0; i<10000; i++) {
            linkedList.add(1, 2);
        }
        end = System.currentTimeMillis();
        System.out.println("LinkedList 중간에 추가하는 시간: " + (end - start)); */

        /* 로또 번호 추첨기 만들기 */
        Scanner scan = new Scanner(System.in);

        int[] lotto = new int[6];
        int len = lotto.length;
        for(int i=0; i<len; i++) {
            try {
                System.out.print("로또 번호 입력: ");
                lotto[i] = scan.nextInt();

                boolean flag = false;   // 중복이 발생 여부
                int j = 0;
                for(; j<i; j++) {
                    if(lotto[i] == lotto[j]) {
                        flag = true;
                        break;
                    }
                }
                if(flag) {
                    System.out.println("중복된 숫자입니다!");
                    i--;
                }

                Set<Integer> set = new TreeSet<>();
                while(set.size() < 6) {
                    System.out.print("로또 번호 입력: ");
                    int temp = scan.nextInt();

                    if(temp < 1 || temp > 45) {
                        System.out.println("1부터 45사이의 숫자를 입력하세요!");
                        continue;
                    }

                    boolean result = set.add(temp);   // 중복검사, set은 중복된 데이터가 안들어간다.
                    if(!result) {
                        System.out.println("중복된 숫자를 입력하면 안됩니다!");
                        continue;
                    }

                    set.add(temp);   // 로또번호 추가
                }

                scan.close();
            } catch(Exception e) {
                i--;
                scan.nextLine();
                System.out.println("숫자를 입력하세요!");
            }
        }

    }
}
