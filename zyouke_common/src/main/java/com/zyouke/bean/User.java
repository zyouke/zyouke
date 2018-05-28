package com.zyouke.bean;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.http.auth.UsernamePasswordCredentials;

/**
 * User.java
 * user 表对应实体
 * @author zyouke
 * @create 2017/11/14 10:21
 */
public class User {

    private long id;
    private String userName;
    private int userAge;
    private String areaCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
