package com.example.suncompany.service;

import com.example.suncompany.entity.User;
import com.example.suncompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById( String id){
        return userRepository.findById(id);
    }

    public User save( User user){
        return userRepository.save(user);
    }

    public void deleteById( String id){
        userRepository.deleteById(id);
    }

}
