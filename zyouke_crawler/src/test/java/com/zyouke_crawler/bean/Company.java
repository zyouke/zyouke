package com.zyouke_crawler.bean;

/**
 * @ClassName: Company 
 * @Description: 公司bean
 * @author 周俊
 * @date 2017年8月22日 下午4:19:32 
 *
 */
public class Company {

    // 省份名称
    private String provinceName;
    // 城市名称
    private String cityName;
    // 公司名称
    private String companyName;
    // 公司地址
    private String companyAddr;
    // 公司联系人
    private String contactPerson;
    // 公司所在地区域编码
    private String companyAreaCode;
    
    //url 请求下一个页面的地址
    private String url;
    
    public String getProvinceName() {
	return provinceName;
    }

    public void setProvinceName(String provinceName) {
	this.provinceName = provinceName;
    }

    public String getCityName() {
	return cityName;
    }

    public void setCityName(String cityName) {
	this.cityName = cityName;
    }

    public String getCompanyName() {
	return companyName;
    }

    public void setCompanyName(String companyName) {
	this.companyName = companyName;
    }

    public String getCompanyAddr() {
	return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
	this.companyAddr = companyAddr;
    }

    public String getContactPerson() {
	return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
	this.contactPerson = contactPerson;
    }

    public String getCompanyAreaCode() {
	return companyAreaCode;
    }

    public void setCompanyAreaCode(String companyAreaCode) {
	this.companyAreaCode = companyAreaCode;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }
}
