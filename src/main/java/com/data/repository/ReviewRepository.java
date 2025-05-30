package com.data.repository;

import com.data.model.Review;

import java.util.List;

public interface ReviewRepository {
    boolean addReview(Review review);
    List<Review> findReviewByProductId(int idProduct);
}
