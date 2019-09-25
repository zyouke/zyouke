package com.zyouke.sort;

import sun.tools.jar.Main;

/**
 * QuickSort.java
 * 快速排序
 * @author zyouke
 * @create 2017/12/11 15:12
 */
public class QuickSort {

    private static int[] arr = {49,38,65,58,15,35,25,53,51};
    private static void sort(int left,int right){
        display();
        int reclos = 0;
        int start = left;
        int end = right;
        int standardIndex = left;
        int standard = arr[standardIndex];
        int arrTemp;
        while (left < right){
            if (arr[left] > standard && arr[right] < standard){
                arrTemp = arr[left];
                arr[left] = arr[right];
                arr[right] = arrTemp;
                left ++;
                right --;
            }else if(arr[left] < standard){
                left ++;
            }else if(arr[right] > standard){
                right --;
            }else {
                left ++;
            }
        }
        if(left == right){
            reclos = left;
            if (arr[left] < standard){
                arrTemp = arr[left];
                arr[left] = standard;
                arr[standardIndex] = arrTemp;
            }
        }

        if (start < reclos){
            sort(start,reclos -1);
        }
        if (end > reclos){
            sort(reclos + 1,end);
        }

    }

    private static void display(){
        for (int i = 0; i < arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] args){
        sort(0,arr.length-1);
    }
}
