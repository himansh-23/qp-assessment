package com.qp.grocery.utility;

import com.qp.grocery.entiry.GroceryDao;
import org.springframework.util.Assert;

import java.util.Objects;

public class GroceryUpdateUtility {

    public static GroceryDao checkChanges(GroceryDao oldObj,GroceryDao newObj){
        if(Objects.nonNull(newObj.getPrice()) && newObj.getPrice() >0){
            oldObj.setPrice(newObj.getPrice());
        }
        if(Objects.nonNull(newObj.getItemName())){
            oldObj.setItemName(newObj.getItemName());
        }
        if(Objects.nonNull(newObj.getQuantity()) && newObj.getQuantity() >0){
            oldObj.setQuantity(newObj.getQuantity());
        }
        return oldObj;

    }
}
