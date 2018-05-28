package com.zyouke.sort;

import com.zyouke.utils.RandomUtil;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * MergeFile.java
 * 归并排序法
 * @author zyouke
 * @create 2017/11/30 10:03
 */
public class MergeFile {

    // 数据文件地址
    private final static String FILE_PATH = "E:/git_repository/zyouke_file/merge/merge_data.txt";
    // 拆分的数据文件前缀
    private final static String FILE_PREFIX = "E:/git_repository/zyouke_file/merge/merge_data_";
    // 拆分的数据结束后缀
    private final static String FILE_POSTFIX = ".txt";
    private final static String FILE_RESULT_PATH = "E:/git_repository/zyouke_file/merge/result.txt";
    // 基础数字
    private final static int baseNum = 10000000;
    /**
     * @Instructions: 清空文件类容
     * @Author: zyouke
     * @Date: 2017/11/30 11:19
     */
    private void cleanFile() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH));
        bufferedWriter.write("");
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    /**
     * @Instructions: 生成数据
     * @Author: zyouke
     * @Date: 2017/11/30 10:06
     */
    private void createMergeData() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH));
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() != baseNum){
            if (System.currentTimeMillis() % 500 == 0){
                System.out.println(set.size());
            }
            int randomNumber = RandomUtil.getRandomNumber(0, baseNum);
            set.add(randomNumber);
        }
        Iterator<Integer> iterator = set.iterator();
        int count = 1;
        while (iterator.hasNext()){
            Integer next = iterator.next();
            bufferedWriter.write(next + "\r\n");
            if (count % 100000 == 0){
                bufferedWriter.flush();
            }
            count ++;
        }
        bufferedWriter.close();
    }

    /**
     * @Instructions: 拆分文件
     * @Author: zyouke
     * @Date: 2017/12/1 16:50
     */
    private void  splitFile() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        SortedSet<Integer> set = new TreeSet<Integer>();
        String line = "";
        int num = 0;
        while ((line = reader.readLine()) != null){
            if (set.size() == baseNum / 10){
                write(num,set);
                num ++;
                set.clear();
            }
            set.add(Integer.valueOf(line));
        }
        if (set.size() > 0){
            write(num,set);
        }
    }

    /**
     * @Instructions: 写文件
     * @Author: zyouke
     * @Date: 2017/12/1 17:17
     */
    private void write(int num,SortedSet<Integer> set) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PREFIX + num + FILE_POSTFIX));
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            bufferedWriter.write(iterator.next()+"\r\n");
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    /**
     * @Instructions:  归并
     * @Author: zyouke
     * @param fileOrder 文件序号
     * @Date: 2017/12/2  16:25
     */
    private void merge(int fileOrder){
        System.out.println("处理"+fileOrder+"号文件");
        try {
            if (fileOrder == 0){
                BufferedReader reader = new BufferedReader(new FileReader(FILE_PREFIX + 0 + FILE_POSTFIX));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_RESULT_PATH));
                String line = "";
                while ((line = reader.readLine()) != null){
                    bufferedWriter.write(line + "\r\n");
                }
                bufferedWriter.flush();
                bufferedWriter.close();
                reader.close();
                new File(FILE_PREFIX + 0 + FILE_POSTFIX).delete();
            }else {
                sort(fileOrder);
            }
            merge(fileOrder + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sort(int fileOrder){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PREFIX + "temp" + FILE_POSTFIX));
            //BufferedReader firstReader = new BufferedReader(new FileReader(FILE_PREFIX + firstFileOrder + FILE_POSTFIX));
            //BufferedReader secondReader = new BufferedReader(new FileReader(FILE_PREFIX + secondFileOrder + FILE_POSTFIX));
            RandomAccessFile firstReader = new RandomAccessFile(FILE_PREFIX + fileOrder + FILE_POSTFIX, "rw");
            RandomAccessFile secondReader = new RandomAccessFile(FILE_RESULT_PATH,"rw");
            String firstLine = null;
            String secondLine = null;
            String firstLastLine = "";
            String secondLastLine = "";
            long firstFilePointer = 0;
            long secondFilePointer = 0;
            while ((firstLine = firstReader.readLine()) != null && (secondLine = secondReader.readLine()) != null){
                if (Integer.valueOf(firstLine) < Integer.valueOf(secondLine)){
                    bufferedWriter.write(firstLine + "\r\n");
                    secondLastLine = secondLine;
                    secondFilePointer = secondReader.getFilePointer() - secondLastLine.length() - 2;
                    secondReader.seek(secondFilePointer);
                }else{
                    firstLastLine = firstLine;
                    firstFilePointer = firstReader.getFilePointer() - firstLastLine.length() - 2;
                    bufferedWriter.write(secondLine+"\r\n");
                    firstReader.seek(firstFilePointer);
                }
            }
            while ((firstLine = firstReader.readLine()) != null){
                bufferedWriter.write(firstLine+"\r\n");
            }
            while ((secondLine = secondReader.readLine()) != null){
                bufferedWriter.write(secondLine+"\r\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            firstReader.close();
            secondReader.close();
            // 将临时文件写到结果文件中
            /*BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_RESULT_PATH));
            writer.write("");
            writer.flush();
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PREFIX + "temp" + FILE_POSTFIX));
            String line = "";
            while ((line = reader.readLine()) != null){
                writer.write(line + "\r\n");
            }
            writer.flush();
            writer.close();
            reader.close();*/
            FileChannel channel = new FileOutputStream(FILE_RESULT_PATH).getChannel();
            channel.write(ByteBuffer.wrap("".getBytes()));
            channel.close();
            FileChannel channelIn = new RandomAccessFile(FILE_PREFIX + "temp" + FILE_POSTFIX,"rw").getChannel();
            FileChannel channelOut = new RandomAccessFile(FILE_RESULT_PATH,"rw").getChannel();
            channelIn.transferTo(0,channelIn.size(),channelOut);
            channelIn.close();
            channelOut.close();
            new File(FILE_PREFIX + "temp" + FILE_POSTFIX).delete();
            new File(FILE_PREFIX + fileOrder + FILE_POSTFIX).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        MergeFile mergeFile = new MergeFile();
        try {
            //mergeFile.cleanFile();
            //mergeFile.createMergeData();
            System.out.println("数据生成完毕!");
            //mergeFile.splitFile();
            mergeFile.merge(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //测试在一个read中是否可以接着读
    /*public static void main(String[] args){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_RESULT_PATH));
            String line = "";
            int lineNum = 0;
            while ((line = reader.readLine()) != null){
                lineNum ++;
                if (line.equals("23456")){
                    reader.mark(lineNum);
                }
                System.out.println(line);
            }
            reader.reset();
            while ((line = reader.readLine()) != null){
                lineNum ++;
                System.out.println(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}
