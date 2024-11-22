package com.project.capstone.controller;

import com.project.capstone.dto.FindDto;
import com.project.capstone.entity.Bus;
import com.project.capstone.entity.User;
import com.project.capstone.repository.UserRepository;
import com.project.capstone.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    BusService busService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/home")
    public String home(Model model){
        String user=returnUsername();
        model.addAttribute("userDetail",user);
        model.addAttribute("findDto",new FindDto());
        return "home";
    }

    @GetMapping("/adminHome")
    public String adminHome(Model model){
        String user=returnUsername();
        model.addAttribute("userDetail",user);
        model.addAttribute("findDto",new FindDto());
        return "adminHome";
    }

    @PostMapping("find")
    public String findBus(@ModelAttribute("findDto")FindDto findDto, Model model){
        List<Bus> option=busService.find(findDto.getSource(),findDto.getDestination(),findDto.getDate());
        model.addAttribute("findBuses",option);
        return "home";
    }

    private String returnUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
        User users = userRepository.findByEmail(user.getUsername()).get();
        return users.getName();
    }
}
