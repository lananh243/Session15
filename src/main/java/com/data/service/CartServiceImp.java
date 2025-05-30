package com.data.service;

import com.data.model.Cart;
import com.data.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartServiceImp implements CartService{
    @Autowired
    private CartRepository cartRepo;
    @Override
    public void addToCart(int userId, int productId) {
        cartRepo.addToCart(userId,productId);
    }

    @Override
    public List<Cart> getCartByUser(int userId) {
        return cartRepo.getCartByUser(userId);
    }

    @Override
    public int getTotalCartPrice(int userId) {
        return cartRepo.getTotalCartPrice(userId);
    }
}
