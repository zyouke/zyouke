package com.zyouke.service;

import com.zyouke.bean.Area;

import java.util.List;

/**
 * 区域的接口
 * @ClassName: IAreaService.java 
 * @Description: TODO
 * @author 周俊
 * @date 2017年10月16日 下午9:51:36
 */
public interface IAreaService {


    /**
     * @Instructions:  查询列表 根据起始行数 和查询总行数
     * @Author: zyouke
     * @Date: 2017/11/15 16:08
     */
    public List<Area> findList(int offset, int rows);

}
