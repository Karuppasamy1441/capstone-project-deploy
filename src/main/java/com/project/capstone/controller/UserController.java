package com.project.capstone.controller;


import com.project.capstone.entity.User;
import com.project.capstone.repository.UserRepository;
import com.project.capstone.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserController {
    private UserServiceImpl userService;
    private UserRepository userRepository;

    @GetMapping("/viewUser")
    public String view(Model model){
        User user=returnUsername();
        model.addAttribute("user",user);
        return "viewUser";
    }




    private User returnUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
        return userRepository.findByEmail(user.getUsername()).get();
    }
}
