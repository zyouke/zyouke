package com.zyouke.service;

import com.zyouke.bean.Area;
import com.zyouke.dao.AreaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 区域服务类
 * @ClassName: AreaService 
 * @author 周俊
 * @date 2017年10月9日 下午2:49:23
 */

@Service("areaService")
public class AreaServiceImpl implements IAreaService{
    @Autowired
    private AreaDao areaDao;


    /**
     * @Instructions:  查询列表 根据起始行数 和查询总行数
     * @Author: zyouke
     * @Date: 2017/11/15 16:08
     */
    @Override
    public List<Area> findList(int offset, int rows) {
        return areaDao.queryListByLimit(offset,rows);
    }

}
