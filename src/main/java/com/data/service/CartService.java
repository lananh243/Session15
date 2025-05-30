package com.data.service;

import com.data.model.Cart;

import java.util.List;

public interface CartService {
    void addToCart(int userId, int productId);
    List<Cart> getCartByUser(int userId);
    int getTotalCartPrice(int userId);
}
