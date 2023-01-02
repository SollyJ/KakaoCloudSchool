package kakao.sollyj.quicksort;

import java.util.Arrays;

public class Quicksort {
    public static void main(String[] args) {
        int[] arr = {161, 166, 165, 155, 173};
        quickSort(0, arr.length-1, arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int left, int right, int[] data) {
        int pivot = left;
        int i = left + 1;
        int j = pivot;

        // 배열의 데이터가 2개 이상인 경우만 수행
        if(left < right) {
            for (; i <= right; i++) {
                if (data[i] < data[pivot]) {    // pivot보다 작은 데이터 발견하면
                    j = j + 1;
                    //스위치
                    int temp = data[j];
                    data[j] = data[i];
                    data[i] = temp;
                }
            }
            // pivot 위치의 데이터를 자신의 위치로 이동
            int temp = data[left];
            data[left] = data[j];
            data[j] = temp;

            // pivot의 위치를 비교가 끝난 자리로 수정
            pivot = j;
            quickSort(left, pivot - 1, data);   // pivot의 왼쪽부분을 재귀적으로 다시 뮉소트
            quickSort(pivot + 1, right, data);   // pivot의 오른쪽 부분을 재귀적으로 다시 퀵소트
        }
    }
}
