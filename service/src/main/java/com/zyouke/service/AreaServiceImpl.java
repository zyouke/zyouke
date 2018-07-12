package com.zyouke.service;

import com.zyouke.bean.Area;
import com.zyouke.dao.AreaDao;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 查询总数
     * @Title: queryCount
     * @author 周俊  void
     */
    public void queryCount(){
    	long count = areaDao.queryCount();
    	System.out.println("查询总数为:"+count);
    }
    
    /**
     * 将区域数据添加到es中
     * @Title: addAreaToEs
     * @author 周俊  void
     */
    public void addAreaToEs(){/*
	ExecutorService executorService = Executors.newFixedThreadPool(15);
	final EsConnectionPool esConnectionPool = EsConnectionPoolFactory.getInstance(15);
	// 删除索引
	Es.deleteIndex(esConnectionPool.getConnection());
	long count = areaDao.queryCount();
	int index = 0;
	if (count % 1000 == 0){
	    index = (int) (count/1000);
	}else{
	    index = (int) ((count - (count % 1000))/1000 + 1);
	}
	for (int i = 0; i < index; i++) {
	    final int temp = i;
	    executorService.execute(new Runnable() {
		int offset = temp * 1000; 
		@Override
		public void run() {
		    List<Area> list = areaDao.queryListByLimit(offset,1000);
		    EsConnection esConnection = esConnectionPool.getConnection();
		    Es.creatIndexByEs(list, esConnection);
		}
	    });
	}
	executorService.shutdown();
	while (!executorService.isTerminated());*/
    }
    
}
