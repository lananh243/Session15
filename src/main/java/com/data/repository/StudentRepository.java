package com.data.repository;

import com.data.model.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> findAllStudent();
}
