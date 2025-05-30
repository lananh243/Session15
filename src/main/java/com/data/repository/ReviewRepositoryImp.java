package com.data.repository;

import com.data.connection.ConnectionDB;
import com.data.model.Review;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepositoryImp implements ReviewRepository {
    @Override
    public boolean addReview(Review review) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result =  false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_review(?,?,?,?)}");
            callSt.setInt(1, review.getIdProduct());
            callSt.setInt(2, review.getIdUser());
            callSt.setInt(3, review.getRating());
            callSt.setString(4, review.getComment());
            callSt.execute();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn);
        }
        return result;
    }

    @Override
    public List<Review> findReviewByProductId(int idProduct) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Review> reviews = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_reviews_by_product_id(?)}");
            callSt.setInt(1, idProduct);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Review review = new Review();
                review.setId(rs.getInt("id"));
                review.setIdProduct(rs.getInt("id_product"));
                review.setIdUser(rs.getInt("id_user"));
                review.setRating(rs.getInt("rating"));
                review.setComment(rs.getString("comment"));
                reviews.add(review);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn);
        }
        return reviews;
    }
}
