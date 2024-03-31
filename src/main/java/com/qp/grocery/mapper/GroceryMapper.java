package com.qp.grocery.mapper;

import com.qp.grocery.dto.GroceryDto;
import com.qp.grocery.entiry.GroceryDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroceryMapper  {

    GroceryMapper Instance = Mappers.getMapper(GroceryMapper.class);

    GroceryDao mapGroceryDtoToDao(GroceryDto groceryDto);
    GroceryDto mapGroceryDaoToDto(GroceryDao groceryDao);

    @Mapping(target = "quantity",ignore = true)
    @Mapping(target = "id",ignore = true)
    GroceryDto mapUserGroceryDto(GroceryDao groceryDao);



}
