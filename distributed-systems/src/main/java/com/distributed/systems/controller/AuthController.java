package com.distributed.systems.controller;

import com.distributed.systems.dto.UserDto;
import com.distributed.systems.entity.User;
import com.distributed.systems.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService){
        this.userService=userService;
    }

    //home page handler
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    //login request handler
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user",user);
        return "register";
    }

    //handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model){

        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if(existingUser!=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email",null,"There is already a user registered with this email");
        }

        if(result.hasErrors()){
            model.addAttribute("user",userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users",users);
        return "users";
    }

}
