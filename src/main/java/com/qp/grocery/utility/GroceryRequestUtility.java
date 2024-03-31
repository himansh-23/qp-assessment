package com.qp.grocery.utility;

import com.qp.grocery.dto.GroceryDto;
import org.springframework.util.Assert;

import java.util.Objects;

public class GroceryRequestUtility {
    public static boolean checkParams(GroceryDto groceryDto) {

       Assert.notNull(groceryDto.getItemName(),"");
       Assert.isTrue(groceryDto.getPrice() >0,"Price should be >0");
       Assert.isTrue(groceryDto.getQuantity() >0,"Quantitly should be >0");
       return true;
    }

}
