package com.zyouke.sort;

/**
 * @Author: zhoujun
 * 选择排序
 * 用当前元素和后面的元素依次比较,如果后面的最小元素小于当前元素则替换,然后进行下次遍历
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 49};
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int flag = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < temp) {
                    temp = arr[j];
                    flag = j;
                }
            }
            if (i != flag){
                arr[flag] = arr[i];
                arr[i] = temp;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print("  ");
        }
    }

}
