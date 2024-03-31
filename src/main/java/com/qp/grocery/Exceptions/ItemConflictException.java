package com.qp.grocery.Exceptions;

import lombok.Getter;

@Getter
public class ItemConflictException extends IllegalArgumentException{

    private String name;

    public ItemConflictException(String name){
        super(name);
        this.name = name;
    }
}
