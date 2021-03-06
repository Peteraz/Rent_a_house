package com.entity.service;

import java.util.List;

import com.entity.domain.Orders;

public interface IOrdersService {
	public Orders getOrdersById(int ordersId);
	public List<Orders> getOrdersByUId(Long UId);
	public void save(Orders orders);
	public void delete(int id);
	public void updateOrderStatus(Orders order);
    public List<Orders> getOrdersByHoId(Long id);
    public List<Orders> getOrderList();
}
