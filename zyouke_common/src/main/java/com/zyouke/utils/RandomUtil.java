package com.zyouke.utils;

import java.util.Random;

/**
 * RandomUtil.java
 * 随机生成数据
 * @author zyouke
 * @create 2017/11/14 14:27
 */
public class RandomUtil {

    private RandomUtil(){}
    private static Random random = new Random();

    /**
     * @Instructions: 产生随机数据
     * @Author: zyouke
     * @Date: 2017/11/14 14:35
     */
    public static int getRandomNumber(){
        int i = random.nextInt(100);
        return i;
    }

    /**
     * @Instructions: 产生随机数据
     * @Author: zyouke
     * @Date: 2017/11/14 14:35
     */
    public static int getRandomNumber(int maxNum){
        int i = random.nextInt(maxNum);
        return i;
    }

    /**
     * @Instructions: 产生随机数据
     * @Author: zyouke
     * @Date: 2017/11/14 14:35
     */
    public static int getRandomNumber(int minNum ,int maxNum){
        while (true){
            int randomNum = random.nextInt(maxNum);
            if(randomNum > minNum || randomNum == minNum){
                return randomNum;
            }
        }
    }


    /**
     * @Instructions: 产生随机数据
     * @Author: zyouke
     * @Param:
     * @Return:
     * @Date: 2017/11/14 14:35
     */
    public static String getRandomString(){
        String[] arr = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        StringBuffer buffer = new StringBuffer();
        for (int i = 0;i< 5; i++){
            buffer.append(arr[random.nextInt(26)]);
        }
        return buffer.toString();
    }

}
