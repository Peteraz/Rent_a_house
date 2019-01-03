package com.entity.service;

import java.util.List;

import com.entity.domain.House;
import com.entity.domain.dto.HouseDto;

public interface IHouseService {
	public House getHouseById(int houseId);
	public List<House> getHouseByHoId(int HoId);
	public List<House> getAll();
	public void save(House house);
	public List<House> findByHouseDto(HouseDto houseDto);
}
