package com.data.controller;

import com.data.dto.StudentDTO;
import com.data.model.Student;
import com.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/list-student")
    public String listStudent(Model model) {
        List<Student> students = studentService.findAllStudent();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        students.forEach(student -> {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(student.getId());
            studentDTO.setName(student.getName());
            studentDTO.setAge(student.getAge());
            studentDTO.setClassStd(student.getClassStd());
            studentDTO.setEmail(student.getEmail());
            studentDTO.setAddress(student.getAddress());
            studentDTO.setPhone(student.getPhone());
            studentDTOS.add(studentDTO);
        });
        model.addAttribute("students", studentDTOS);
        return "list_student";
    }
}
