package com.qp.grocery.service.impl;

import com.qp.grocery.Exceptions.ItemConflictException;
import com.qp.grocery.dto.GroceryDto;
import com.qp.grocery.entiry.GroceryDao;
import com.qp.grocery.mapper.GroceryMapper;
import com.qp.grocery.repository.GroceryRepository;
import com.qp.grocery.service.GroceryService;
import com.qp.grocery.utility.GroceryUpdateUtility;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GroceryServiceImpl implements GroceryService {

    @Autowired
    private GroceryRepository groceryRepository;
    @Override
    public Optional<GroceryDto> addGrocery(GroceryDto groceryDto) {
        Optional<GroceryDao> OptionalgroceryDao =  groceryRepository.findOneByItemName(groceryDto.getItemName());
        if(OptionalgroceryDao.isPresent()){
            throw new ItemConflictException("Item already present with this name Please give different Name.");
        }
        GroceryDao groceryDao =  GroceryMapper.Instance.mapGroceryDtoToDao(groceryDto);
        return Optional.of(groceryRepository.save(groceryDao))
                .map(GroceryMapper.Instance::mapGroceryDaoToDto);


    }

    @Override
    public List<GroceryDto> getGroceryList() {
        return groceryRepository.findAll().stream().map(
                GroceryMapper.Instance::mapGroceryDaoToDto
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<GroceryDto> deleteGrocery(String groceryDto) {
        return
                groceryRepository.deleteByItemName(groceryDto).stream()
                        .map(GroceryMapper.Instance::mapGroceryDaoToDto).findFirst();
    }

    @Override
    public Optional<GroceryDto> updateExistingGrocery(String id,GroceryDto groceryDto) {
        String updatingGroceryId = groceryDto.getId();
                return groceryRepository.findById(id)
                        .map(existingGrocery ->
                                {
                                    GroceryDao updatedObj = GroceryUpdateUtility.checkChanges(existingGrocery,GroceryMapper.Instance.mapGroceryDtoToDao(groceryDto));
                                    return Optional.of(groceryRepository.save(updatedObj)).map(GroceryMapper.Instance::mapGroceryDaoToDto);
                                }).orElseThrow(()->new IllegalArgumentException("No grocery found with this id"));
    }

    @Override
    public List<GroceryDto> getGroceryListForUser() {
        return groceryRepository.findAll().stream().filter(grocery -> grocery.getQuantity()>0).map(
                GroceryMapper.Instance::mapUserGroceryDto
        ).collect(Collectors.toList());
    }

    @Override
    public Map<String, Integer> orderGrocery(Map<String, Integer> orderMap) {
        Map<String,Integer> bookGrocery= new HashMap<>();
        log.info(orderMap.keySet().toString());
        Set<GroceryDao> groceryDaoSet = groceryRepository.findByItemNameIn(orderMap.keySet())
                .stream().map(
                        singleGrocery-> {
                            int quantityRequired = orderMap.get(singleGrocery.getItemName());
                            if(quantityRequired<= singleGrocery.getQuantity()){
                                int quantityLeft = singleGrocery.getQuantity() - quantityRequired;
                                singleGrocery.setQuantity(quantityLeft);
                                bookGrocery.put(singleGrocery.getItemName(),quantityRequired);
                            }
                            return singleGrocery;
                        }
                ).collect(Collectors.toSet());
        log.info(groceryDaoSet.toString());
        groceryRepository.saveAll(groceryDaoSet);
        return bookGrocery;
    }
}
