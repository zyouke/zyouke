package com.zyouke.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.*;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zyouke.bean.Area;


@Repository("areaDao")
public class AreaDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(Area area) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(area);
        tx.commit();
        session.close();
    }

    public void addAll(List<Area> areas) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        for (Area area : areas) {
            session.save(area);
        }
        session.flush();
        session.clear();
        tx.commit();
        session.close();
    }

    public synchronized void addAllSql(List<Area> areas) {
        System.out.println("----------->" + areas.get(0).getCode());
        String sqlInsert = "INSERT INTO t_area (CODE,VALUE,PARENT,LEVEL,FULL_NAME) values ";
        StringBuffer buffer = new StringBuffer(sqlInsert);
        for (int i = 0; i < areas.size(); i++) {
            Area area = areas.get(i);
            if (i == areas.size() - 1) {
                buffer.append("(");
                buffer.append("'" + area.getCode() + "',");
                buffer.append("'" + area.getValue() + "',");
                buffer.append("'" + area.getParent() + "',");
                buffer.append(area.getLevel() + ",");
                buffer.append("'" + area.getFullName() + "'");
                buffer.append(");");
            } else {
                buffer.append("(");
                buffer.append("'" + area.getCode() + "',");
                buffer.append("'" + area.getValue() + "',");
                buffer.append("'" + area.getParent() + "',");
                buffer.append(area.getLevel() + ",");
                buffer.append("'" + area.getFullName() + "'");
                buffer.append("),");
            }
        }
        try {
            FileWriter fileWritter = new FileWriter("E:/work_doc/demo_file/area_file/t_area.txt", true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(buffer.toString() + "\r\n");
            bufferWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询数量
     *
     * @return int
     * @Title: queryCount
     * @author 周俊
     */
    public long queryCount() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String sqlSelect = "select count(id) from t_area";
        BigInteger resultCount = (BigInteger) session.createSQLQuery(sqlSelect).uniqueResult();
        session.close();
        return Long.valueOf(String.valueOf(resultCount));
    }

    /**
     * @return List<Area>
     * @Title: queryListByLimit
     * @author 周俊
     */
    public List<Area> queryListByLimit(int offset, int rows) {
        Session session = sessionFactory.getCurrentSession();
        String sqlSelect = "select * from T_AREA  LIMIT " + offset + "," + rows;
        Query query = session.createSQLQuery(sqlSelect).addEntity(Area.class);
        List<Area> list = query.list();
        return list;
    }

    /**
     * @Instructions: 查询id集合
     * @Author: zyouke
     * @Date: 2017/12/13 9:50
     */
    public List<Long> queryIdList(int offset) {
        Session session = sessionFactory.openSession();
        String sqlSelect = "select ID from T_AREA limit "+offset+",100000";
        Query query = session.createSQLQuery(sqlSelect).addScalar("ID", LongType.INSTANCE);
        List<Long> list = query.list();
        return list;
    }

    /**
     * 查询区域
     * @return
     */
    public List<String> queryAreaCodes() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT `CODE` FROM T_AREA").addScalar("CODE", StringType.INSTANCE);
        List list = query.list();
        session.close();
        return list;
    }
}
