package com.zyouke.sort;

/**
 * 插入排序
 *1，从第一个元素开始，该元素可以认为已经被排序
 *2，取出下一个元素，在已经排序的元素序列中从后向前扫描,
 * 3，如果当前元素大于前一个元素则替换位置
 */
public class InsertionSort {

    public static void main(String[] args){
        int[] arr = {49, 38, 65, 58, 15, 35, 25, 53, 51};
        // arr[i]
        for (int i = 1; i < arr.length; i++) {
            int arri = arr[i];
            if (arri < arr[i - 1]) {
                for (int j = i - 1; j >= 0; j--) {
                    int arrj = arr[j];
                    if (arri < arrj){
                        arr[j+1] = arrj;
                        arr[j] = arri;
                    }
                }
            }
        }
        for (int i : arr) {
            System.out.print(i);
            System.out.print(",");
        }
    }
}
