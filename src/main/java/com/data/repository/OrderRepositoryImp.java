package com.data.repository;

import com.data.connection.ConnectionDB;
import com.data.model.Order;

import java.sql.CallableStatement;
import java.sql.Connection;

public class OrderRepositoryImp implements  OrderRepository {
    @Override
    public void addOrder(Order order) {
        Connection conn =  null;
        CallableStatement stmt = null;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{call add_order_with_details(?,?,?,?)}");
            stmt.setInt(1, order.getOrderId());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }
}
