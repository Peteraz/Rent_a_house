package com.entity.domain.dto;

import java.util.Date;

public class OrdersDto {
    private Integer orderId;

    private String orderStatus;

    private Date orderTime;

    private String cpersonName;

    private String cNumber;
    
    private String address;
    
    private Integer hArea;
    
    private Integer hPrice;
    
    private String hName;
    
    private String hPhonenumber;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getCpersonName() {
		return cpersonName;
	}

	public void setCpersonName(String cpersonName) {
		this.cpersonName = cpersonName;
	}

	public String getcNumber() {
		return cNumber;
	}

	public void setcNumber(String cNumber) {
		this.cNumber = cNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer gethArea() {
		return hArea;
	}

	public void sethArea(Integer hArea) {
		this.hArea = hArea;
	}

	public Integer gethPrice() {
		return hPrice;
	}

	public void sethPrice(Integer hPrice) {
		this.hPrice = hPrice;
	}

	public String gethName() {
		return hName;
	}

	public void sethName(String hName) {
		this.hName = hName;
	}

	public String gethPhonenumber() {
		return hPhonenumber;
	}

	public void sethPhonenumber(String hPhonenumber) {
		this.hPhonenumber = hPhonenumber;
	}
}