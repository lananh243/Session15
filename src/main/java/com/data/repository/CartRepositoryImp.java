package com.data.repository;

import com.data.connection.ConnectionDB;
import com.data.model.Cart;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CartRepositoryImp implements  CartRepository {
    @Override
    public void addToCart(int userId, int productId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_to_cart(?, ?)}");
            callSt.setInt(1, userId);
            callSt.setInt(2, productId);
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    @Override
    public List<Cart> getCartByUser(int userId) {
        List<Cart> cartList = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_cart_by_user(?)}");
            callSt.setInt(1, userId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setIdCart(rs.getInt("id_cart"));
                cart.setIdProduct(rs.getInt("id_product"));
                cart.setQuantity(rs.getInt("quantity"));
                cart.setTotal(rs.getInt("total"));
                cartList.add(cart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return cartList;
    }

    @Override
    public int getTotalCartPrice(int userId) {
        int totalPrice = 0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_total_cart_price(?)}");
            callSt.setInt(1, userId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                totalPrice = rs.getInt("total_price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return totalPrice;
    }
}
