package com.data.controller;

import com.data.model.Cart;
import com.data.model.User;
import com.data.service.CartService;
import com.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/cart")
    public String cartForm(Model model) {
        int userId = 1;
        List<Cart> carts = cartService.getCartByUser(userId);
        int total = cartService.getTotalCartPrice(userId);

        model.addAttribute("carts", carts);
        model.addAttribute("total", total);
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") int productId) {
        int userId = 1;
        cartService.addToCart(userId, productId);
        return "redirect:/cart";
    }

}
