package com.data.repository;

import com.data.connection.ConnectionDB;
import com.data.model.User;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImp implements UserRepository {
    @Override
    public boolean save(User user) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_user(?,?,?)}");
            callSt.setString(1, user.getUsername());
            callSt.setString(2, user.getEmail());
            callSt.setString(3, user.getPassword());
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
    public List<User> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<User> userList = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_all_user()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                userList.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn);
        }
        return userList;
    }
}
