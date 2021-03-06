package com.entity.IDao;

import java.util.List;

import com.entity.domain.House;
import com.entity.domain.dto.HouseDto;

public interface IHouseDao {
    int deleteByPrimaryKey(Integer houseid);

    int insert(House record);

    int insertSelective(House record);

    House selectByPrimaryKey(Integer houseid);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
    
    public List<House> getAll();
    
    public List<House> findByHouseDto(HouseDto houseDto);
    
	public List<House> getHouseByHoId(int HoId);
}