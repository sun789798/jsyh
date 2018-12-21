package com.example.jsdengluprovider.pojo;

import java.io.Serializable;

/**
 * (Jsclientbank)实体类
 *
 * @author makejava
 * @since 2018-12-19 23:36:45
 */
public class Jsclientbank implements Serializable {
    private static final long serialVersionUID = 313088802613570785L;
    //银行卡号
    private String jsBanknumber;
    //银行卡类型
    private Integer jsBanktype;
    //银行卡密码
    private String jsBankpassword;
    //客户后台id
    private Integer jsClientid;
    //银行卡余额
    private Double jsBankremainingsum;
    //银行卡状态 0正常 -1挂失 1锁定
    private Integer jsBankstate;
    //银行卡预留手机号码
    private String jsBankphone;
    //身份证号码
    private String jsIdnumber;

    public String getJsIdnumber() {
        return jsIdnumber;
    }

    public void setJsIdnumber(String jsIdnumber) {
        this.jsIdnumber = jsIdnumber;
    }

    public String getJsBankphone() {
        return jsBankphone;
    }

    public void setJsBankphone(String jsBankphone) {
        this.jsBankphone = jsBankphone;
    }

    public String getJsBanknumber() {
        return jsBanknumber;
    }

    public void setJsBanknumber(String jsBanknumber) {
        this.jsBanknumber = jsBanknumber;
    }

    public Integer getJsBanktype() {
        return jsBanktype;
    }

    public void setJsBanktype(Integer jsBanktype) {
        this.jsBanktype = jsBanktype;
    }

    public String getJsBankpassword() {
        return jsBankpassword;
    }

    public void setJsBankpassword(String jsBankpassword) {
        this.jsBankpassword = jsBankpassword;
    }

    public Integer getJsClientid() {
        return jsClientid;
    }

    public void setJsClientid(Integer jsClientid) {
        this.jsClientid = jsClientid;
    }

    public Double getJsBankremainingsum() {
        return jsBankremainingsum;
    }

    public void setJsBankremainingsum(Double jsBankremainingsum) {
        this.jsBankremainingsum = jsBankremainingsum;
    }

    public Integer getJsBankstate() {
        return jsBankstate;
    }

    public void setJsBankstate(Integer jsBankstate) {
        this.jsBankstate = jsBankstate;
    }

}