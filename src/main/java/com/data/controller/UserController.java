package com.data.controller;

import com.data.connection.ConnectionDB;
import com.data.dto.UserDTO;
import com.data.model.User;
import com.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user-add")
    public String userAdd(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "user_form";
    }
    @GetMapping("/list-user")
    public String listStudent(Model model) {
        List<User> users = userService.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        users.forEach(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword());
            userDTOS.add(userDTO);
        });
        model.addAttribute("users", userDTOS);
        return "result";
    }
    @PostMapping("/user-save")
    public String save(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                       BindingResult result){
        if(result.hasErrors()){
            return "user_form";
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        userService.save(user);
        return "redirect:/list-user";
    }
}
