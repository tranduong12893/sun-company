package com.example.suncompany.controller;


import com.example.suncompany.entity.User;
import com.example.suncompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * http://localhost:8080/api/V1/users      |   GET     | return list user
 * http://localhost:8080/api/V1/users      |   POST    | create new user
 * http://localhost:8080/api/V1/users/1    |   DELETE  | remove user
 * http://localhost:8080/api/V1/users/1    |   GET     | find user by id
 * http://localhost:8080/api/V1/users/1    |   PUT     | update user..
 */


@CrossOrigin(value = "*")
@RestController
@RequestMapping(path = "api/v1/users")
public class userController {
    //CRUD
    @Autowired
    UserService userService;
//    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getList(){
        return ResponseEntity.ok(userService.findAll());
    }
    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> findById(@PathVariable String id){
        Optional<User> optionalUser = userService.findById(id);
        if(!optionalUser.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalUser.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> save(@RequestBody User user){
        return ResponseEntity.ok(userService.save(user));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity deleteById( @PathVariable String id){
        if (!userService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody User updateUser){
        Optional<User> optionalUser = userService.findById(id);
        if(!optionalUser.isPresent()){
            ResponseEntity.badRequest().build();
        }
        User existingUser = optionalUser.get();
        existingUser.setFullName(updateUser.getFullName());
        existingUser.setWage(updateUser.getWage());
        return ResponseEntity.ok(userService.save(existingUser));
    }
}
