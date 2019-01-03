package com.entity.domain;

import java.util.Date;

public class Orders {
    private Integer orderid;

    private String orderstatus;

    private Date ordertime;

    private Integer opid;
    
    private Integer hoid;		

    private Integer houseid;

    private String cpersonname;

    private String cnumber;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus == null ? null : orderstatus.trim();
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Integer getOpid() {
        return opid;
    }

    public void setOpid(Integer opid) {
        this.opid = opid;
    }

    public Integer getHouseid() {
        return houseid;
    }

    public void setHouseid(Integer houseid) {
        this.houseid = houseid;
    }

    public String getCpersonname() {
        return cpersonname;
    }

    public void setCpersonname(String cpersonname) {
        this.cpersonname = cpersonname == null ? null : cpersonname.trim();
    }

    public String getCnumber() {
        return cnumber;
    }

    public void setCnumber(String cnumber) {
        this.cnumber = cnumber == null ? null : cnumber.trim();
    }

	public Integer getHoid() {
		return hoid;
	}

	public void setHoid(Integer hoid) {
		this.hoid = hoid;
	}
}