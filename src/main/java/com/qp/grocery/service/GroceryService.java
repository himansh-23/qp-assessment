package com.qp.grocery.service;

import com.qp.grocery.dto.GroceryDto;
import com.qp.grocery.entiry.GroceryDao;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GroceryService {

     Optional<GroceryDto> addGrocery(GroceryDto groceryDto);

     List<GroceryDto> getGroceryList();

     Optional<GroceryDto> deleteGrocery(String groceryDto);

     Optional<GroceryDto> updateExistingGrocery(String id,GroceryDto groceryDto);

     List<GroceryDto> getGroceryListForUser();

     Map<String, Integer> orderGrocery(Map<String,Integer>  orderMap);

}
