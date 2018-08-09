package com.zyouke.dubbo.service;

import com.zyouke.bean.Area;
import com.zyouke.service.IAreaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhoujun
 */
@Service("areaService")
public class AreaServiceImpl implements IAreaService{
    @Override
    public List<Area> findList(int offset, int rows) {
        List<Area> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Area area = new Area();
            area.setCode("111111111"+i);
            area.setValue("北京"+i);
            list.add(area);
        }
        return list;
    }
}
