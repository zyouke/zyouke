package com.service.test.d_20171230;

import com.zyouke.utils.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// ucenter 项目的测试
public class UcenterTest {

    private final static String TEST_BASEURL = "http://ucms.test.faxuan.net";
    private final static String DEV_BASEURL = "http://devcloudpay.faxuan.net";
    private final static String LOC_BASEURL = "http://127.0.0.1";
    @Test
    public void test11() {
        String url = "http://127.0.0.1/ucds/ucenter/addUser.do";
        Map<String, String> map = new HashMap<String, String>();
        map.put("userAccount", "ucenter0");
        map.put("sysCode", "FX001");
        map.put("userPassword", "123");
        String result = HttpClientUtil.doPost(url, map);
        System.out.print(result);
    }

    @Test
    public void test23() {
        String url = TEST_BASEURL + "/ucds/ucenter/registerUser.do";
        Map<String, String> map = new HashMap<String, String>();
        map.put("userAccount", "zhoujun10 ");
        map.put("userPhone","12345678911");
        map.put("userPassword","123456789");
        map.put("sysCode", "wh0122");
        String result = HttpClientUtil.doPost(url, map);
        System.out.print(result);
    }

    @Test
    public void test12() {
        String url = LOC_BASEURL + "/ucds/ucenter/batchAddUser.do";
        Map<String, String> map = new HashMap<String, String>();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 200; i++) {
            if (i < 199) {
                buffer.append("ucenterZ" + i + ",");
            } else {
                buffer.append("ucenterZ" + i);
            }
        }
        map.put("userAccounts", buffer.toString());
        map.put("sysCode", "FX001");
        map.put("userPassword", "123");
        String result = HttpClientUtil.doPost(url, map);
        System.out.print(result);
    }

    @Test
    public void test13() {
        String url = "http://127.0.0.1/ucds/ucenter/getUserList.do";
        Map<String, String> map = new HashMap<String, String>();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            if (i < 499) {
                buffer.append("zhangsanceshi" + i + ",");
            } else {
                buffer.append("zhangsanceshi" + i);
            }
        }
        map.put("userAccounts", buffer.toString());
        map.put("sysCode", "FX02");
        String result = HttpClientUtil.doPost(url, map);
        System.out.print(result);
    }

    @Test
    public void test14() {
        String url = "http://ucms.test.faxuan.net/ucds/ucenter/getUserDetail.do";
        Map<String, String> map = new HashMap<String, String>();
        map.put("userAccount","30");
        map.put("sysCode", "kf002");
        String result = HttpClientUtil.doPost(url, map);
        System.out.print(result);
    }

    @Test
    public void test15() {
        String url = "http://ucms.test.faxuan.net/ucds/ucenter/doBatchUserDelete.do";
        Map<String, String> map = new HashMap<String, String>();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 500; i++) {
            if (i < 499) {
                buffer.append("ucenterA" + i + ",");
            } else {
                buffer.append("ucenterA" + i);
            }
        }
        map.put("userAccounts", buffer.toString());
        map.put("sysCode", "FX001");
        String result = HttpClientUtil.doPost(url, map);
        System.out.print(result);
    }

    @Test
    public void test15_1() {
        String url = "http://devcloudpay.faxuan.net/ucds/ucenter/doBatchUserDelete.do";
        Map<String, String> map = new HashMap<String, String>();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 2000; i++) {
            if (i < 1999) {
                buffer.append("ucenter" + i + ",");
            } else {
                buffer.append("ucenter" + i);
            }
        }
        map.put("userAccounts", buffer.toString());
        map.put("sysCode", "FX001");
        String result = HttpClientUtil.doPost(url, map);
        System.out.print(result);
    }

    @Test
    public void test16() {
        String url = "http://127.0.0.1/ucds/ucenter/doDeleteUser.do";
        Map<String, String> map = new HashMap<String, String>();
        map.put("userAccount","ucenter0");
        map.put("sysCode", "FX001");
        String result = HttpClientUtil.doPost(url, map);
        System.out.print(result);
    }

    @Test
    public void test17() {
        int threadNum = 800;
        final CountDownLatch downLatch = new CountDownLatch(threadNum);
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        for (int i = 0; i < threadNum; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        downLatch.await();
                        send(UUID.randomUUID().toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            downLatch.countDown();
        }
        executorService.shutdown();
        while (!executorService.isTerminated());
        System.out.println("执行完成了");
    }

    private void send(String adminAccount){
        String url = "http://127.0.0.1/ucds/admin/getAdmin.do";
        Map<String, String> map = new HashMap<String, String>();
        map.put("adminAccount", adminAccount);
        String result = HttpClientUtil.doPost(url, map);
        System.out.print(result);
    }
    @Test
    public void test18() {
        String url = DEV_BASEURL + "/ucds/ucenter/repeatCheck.do";
        Map<String, String> map = new HashMap<String, String>();
        map.put("userAccount","32000");
        map.put("sysCode", "FX001");
        map.put("type","1");
        String result = HttpClientUtil.doPost(url, map);
        System.out.print(result);
    }

    @Test
    public void test19() {
        String url = LOC_BASEURL + "/ucds/ucenter/bindUser.do";
        Map<String, String> map = new HashMap<String, String>();
        map.put("userAccount","100003");
        map.put("bindCode", "969@qq.com");
        map.put("sysCode", "FX002");
        map.put("bindType", "3");
        String result = HttpClientUtil.doPost(url, map);
        System.out.print(result);
    }

    @Test
    public void test20() {
        String url = LOC_BASEURL + "/ucds/ucenter/unbindUser.do";
        Map<String, String> map = new HashMap<String, String>();
        map.put("userAccount","32000");
        map.put("unbindCode", "18600695062");
        map.put("sysCode", "kkkx1");
        map.put("unbindType", "2");
        String result = HttpClientUtil.doPost(url, map);
        System.out.print(result);
    }

    @Test
    public void test21() {
        String url = "http://127.0.0.1/ucds/ucenter/repeatCheckUserAccountAndLoginCode.do";
        Map<String, String> map = new HashMap<String, String>();
        map.put("userAccount","ucenter0");
        map.put("resPassWord", "123");
        map.put("sysCode", "FX001");
        String result = HttpClientUtil.doPost(url, map);
        System.out.print(result);
    }

    @Test
    public void test22() {
        String url = "http://127.0.0.1/ucds/ucenter/resetUserPassword.do";
        Map<String, String> map = new HashMap<String, String>();
        map.put("userAccount","ks12");
        map.put("loginCode", "18810301738");
        map.put("sysCode", "FX02");
        map.put("type", "2");
        String result = HttpClientUtil.doPost(url, map);
        System.out.print(result);
    }
}
