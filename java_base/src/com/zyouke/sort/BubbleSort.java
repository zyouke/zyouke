package com.zyouke.sort;

/**
 * @Author: zhoujun
 * 冒泡排序
 * 针对每一个元素,相邻的元素根据大小交换位置
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 49};
        for (int i = 0; i < arr.length -1; i++) {
            for (int j = 0; j < arr.length -1 ; j++) {
                if (arr[j+1] < arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(" ");
        }
    }
}
