package com.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private int idCart;
    private int idProduct;
    private int idUser;
    private int quantity;
    private int total;
}
