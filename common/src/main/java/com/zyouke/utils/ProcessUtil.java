package com.zyouke.utils;

import java.io.IOException;

/**
 * 查看进程的工具
 */
public class ProcessUtil {
    private final static Runtime RUNTIME = Runtime.getRuntime();

    public static int getProcess(){
        try {
            Process process = RUNTIME.exec("ps aux|grep framework-1.0-SNAPSHOT.jar|awk '{print $2}'");
            int exitValue = process.exitValue();
            return exitValue;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
