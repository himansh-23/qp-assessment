package com.qp.grocery.controller;

import com.qp.grocery.dto.GroceryDto;
import com.qp.grocery.service.GroceryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/v1")
@Slf4j
public class UserController {
    @Autowired
    private GroceryService groceryService;

    @GetMapping("/user/list")
    public ResponseEntity<List<GroceryDto>> getExistingGroceryItems(){

        try {
            List<GroceryDto> groceryDto1 = groceryService.getGroceryListForUser();
            return ResponseEntity.ok(groceryDto1);
        } catch (Exception e){
            return ResponseEntity.badRequest().header("X-error", e.getMessage()).build();
        }
    }

    @PostMapping("/book/grocery")
    public ResponseEntity<Map<String, Integer>> getExistingGroceryItems(@RequestBody Map<String,Integer> orderBook){

        try {
            Map<String, Integer> bookedGrocery = groceryService.orderGrocery(orderBook);
            return ResponseEntity.ok(bookedGrocery);
        } catch (Exception e){
            return ResponseEntity.badRequest().header("X-error", e.getMessage()).build();
        }
    }

}
