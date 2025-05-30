package com.data.service;

import com.data.model.Review;

import java.util.List;

public interface ReviewService {
    boolean addReview(Review review);
    List<Review> findReviewByProductId(int idProduct);
}
