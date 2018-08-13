package com.zyouke.dubbo.base.spi.job;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;

/**
 * @Author: zhoujun
 */
/**
 * 使用value时,当url 中存在key为 value时激活,但是不参与排序
 * url = url.addParameter(Constants.TOKEN_KEY,"teacher");
 * url 中的key 必须为定义spi 时的key,不然在获取spi的扩展时,因找不到而报错
 *
 * 使用before时,表示teacher 在farmer之前
 * 使用after时,表示teacher 在computer之后
 * before after 与order混用时按before  after规则
  */
 @Activate(group = {"job_group"},before = "farmer",after = "computer")
public class Teacher implements Job{
    @Override
    public void work() {
        System.out.println("老师在上课.....");
    }
}
