package com.FutureBatch3.JPAAssignment.Controller;

import com.FutureBatch3.JPAAssignment.ResourceNotFoundException;
import com.FutureBatch3.JPAAssignment.Model.User;
import com.FutureBatch3.JPAAssignment.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public Page<User> getUser(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    @PostMapping("/user")
    public User createUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/user/{userId}")
    public User updateUser(@PathVariable Long userId, @Valid @RequestBody User userRequest){
        return userRepository.findById(userId).map(user -> {
                    user.setName(userRequest.getName());
                    user.setJob(userRequest.getJob());
                    return userRepository.save(user);
        }).orElseThrow( () -> new ResourceNotFoundException("User not found with id" + userId));
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Question not found with Id " + userId));
    }
}
