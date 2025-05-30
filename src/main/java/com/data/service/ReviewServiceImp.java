package com.data.service;

import com.data.model.Review;
import com.data.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepo;
    @Override
    public boolean addReview(Review review) {
        return reviewRepo.addReview(review);
    }

    @Override
    public List<Review> findReviewByProductId(int idProduct) {
        return reviewRepo.findReviewByProductId(idProduct);
    }


}
