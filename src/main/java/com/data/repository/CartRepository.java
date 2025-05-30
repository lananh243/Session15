package com.data.repository;

import com.data.model.Cart;

import java.util.List;

public interface CartRepository {
    void addToCart(int userId, int productId);
    List<Cart> getCartByUser(int userId);
    int getTotalCartPrice(int userId);
}
