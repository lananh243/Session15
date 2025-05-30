package com.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private int id;
    private int idProduct;
    private int idUser;
    private int rating;
    private String comment;
}
