package com.data.repository;

import com.data.connection.ConnectionDB;
import com.data.model.Student;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepositoryImp implements  StudentRepository {

    @Override
    public List<Student> findAllStudent() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> studentList = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_student()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("std_name"));
                student.setAge(rs.getInt("age"));
                student.setClassStd(rs.getString("class"));
                student.setEmail(rs.getString("email"));
                student.setAddress(rs.getString("address"));
                student.setPhone(rs.getString("phone"));
                studentList.add(student);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn);
        }
        return studentList;
    }
}
