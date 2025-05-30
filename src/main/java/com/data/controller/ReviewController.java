package com.data.controller;

import com.data.dto.ReviewDTO;
import com.data.model.Product;
import com.data.model.Review;
import com.data.model.User;
import com.data.service.ProductService;
import com.data.service.ReviewService;
import com.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("review-form")
    public String reviewForm(Model model) {
        ReviewDTO reviewDTO = new ReviewDTO();
        model.addAttribute("reviewDTO", reviewDTO);

        List<User> users = userService.findAll();
        List<Product> products = productService.findAll();

        model.addAttribute("users", users);
        model.addAttribute("products", products);

        return "review_form";
    }


    @PostMapping("/review-save")
    public String reviewSave(@ModelAttribute("reviewDTO") ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setIdProduct(reviewDTO.getIdProduct());
        review.setIdUser(reviewDTO.getIdUser());
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        reviewService.addReview(review);
        return "redirect:/product-detail/" + reviewDTO.getIdProduct();
    }
}
