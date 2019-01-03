package com.entity.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.IDao.IOrdersDao;
import com.entity.domain.Orders;
import com.entity.service.IOrdersService;
  
@Service("ordersService")  
public class OrdersServiceImpl implements IOrdersService {  
    @Resource  
    private IOrdersDao ordersDao;  
    public Orders getOrdersById(int ordersId) {  
        return ordersDao.selectByPrimaryKey(ordersId);  
    }
    public void save(Orders orders) {  
        ordersDao.insert(orders);  
    }
    public List<Orders> getOrdersByUId(Long UId){
    	return ordersDao.getOrdersByUId(UId);
    }
	public void delete(int id){
		ordersDao.deleteByPrimaryKey(id);
	}
	public void updateOrderStatus(Orders order){
		ordersDao.updateByPrimaryKey(order);
	}
    public List<Orders> getOrdersByHoId(Long id){
    	return ordersDao.getOrdersByHoId(id);
    }
    public List<Orders> getOrderList(){
    	return ordersDao.getOrderList();
    }
}