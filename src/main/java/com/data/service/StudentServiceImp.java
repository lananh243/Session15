package com.data.service;

import com.data.model.Student;
import com.data.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService{
    @Autowired
    private StudentRepository studentRepo;

    @Override
    public List<Student> findAllStudent() {
        return studentRepo.findAllStudent();
    }
}
