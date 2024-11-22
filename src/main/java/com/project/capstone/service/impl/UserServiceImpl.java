package com.project.capstone.service.impl;

import com.project.capstone.entity.User;
import com.project.capstone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl {
    private UserRepository userRepository;

    public User getByEmail(String email){
        return userRepository.findByEmail(email).get();
    }
    public void saveUser(User user){
        userRepository.save(user);
    }

}
