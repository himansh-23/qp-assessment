package com.qp.grocery.repository;

import com.qp.grocery.entiry.GroceryDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


/*
 Grocery Repository.
 */
public interface GroceryRepository extends JpaRepository<GroceryDao,String> {

    public Optional<GroceryDao> findOneByItemName(String itemName);

    public List<GroceryDao> deleteByItemName(String itemName);

    public List<GroceryDao> findByItemNameIn(Set<String> itemName);

}
