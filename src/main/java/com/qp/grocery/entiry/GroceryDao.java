package com.qp.grocery.entiry;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Generated;

import java.io.Serializable;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroceryDao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String itemName;
    private Integer price;
    private Integer quantity;

}
