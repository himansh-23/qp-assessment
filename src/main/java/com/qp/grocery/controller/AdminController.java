package com.qp.grocery.controller;

import com.qp.grocery.Exceptions.ItemConflictException;
import com.qp.grocery.dto.GroceryDto;
import com.qp.grocery.service.GroceryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/api/v1")
@Slf4j
public class AdminController {

    @Autowired
    private GroceryService groceryService;
    @PostMapping("/add/grocery")
    public ResponseEntity<GroceryDto> addGroceryToInventary(@RequestBody GroceryDto groceryDto){

        try {
            Optional<GroceryDto> groceryDto1 = groceryService.addGrocery(groceryDto);
            return groceryDto1.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().header("X-error", "error while saving object to db").build());
        } catch(ItemConflictException e){
            return ResponseEntity.badRequest().header("X-error", "Item already present with same name;").build();
        }  catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().header("X-error", "Some issue with input data {"+e.getMessage()+" }").build();
        } catch (Exception e){
            return ResponseEntity.badRequest().header("X-error", e.getMessage()).build();
        }

    }

    @GetMapping("/list")
    public ResponseEntity<List<GroceryDto>> getExistingGroceryItems(){

        try {
            List<GroceryDto> groceryDto1 = groceryService.getGroceryList();
            return ResponseEntity.ok(groceryDto1);
        } catch (Exception e){
            return ResponseEntity.badRequest().header("X-error", e.getMessage()).build();
        }
    }

    @DeleteMapping("/remove/grocery/{groceryName}")
    public ResponseEntity<GroceryDto> deleteExistingGroceryItems(@PathVariable("groceryName") String groceryName){

        try {
            log.info(groceryName);
            Optional<GroceryDto> groceryDto1 = groceryService.deleteGrocery(groceryName);
            return groceryDto1.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().header("X-error", "Item not present in db").build());
        } catch (Exception e){
            return ResponseEntity.badRequest().header("X-error", e.getMessage()).build();
        }
    }

    @PatchMapping("/update/grocery/{id}")
    public ResponseEntity<GroceryDto> updateGroceryDetails(@PathVariable("id")String id,@RequestBody GroceryDto groceryDto){

        try{
            log.info("updating existing grocery details");
            Optional<GroceryDto> groceryDto1 = groceryService.updateExistingGrocery(id,groceryDto);
            return groceryDto1.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().header("X-error", "Item not present in db").build());

        }catch (Exception e){
            return ResponseEntity.badRequest().header("X-error", e.getMessage()).build();
        }
    }
}
