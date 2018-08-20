package com.FutureBatch3.JPAAssignment.Controller;


import com.FutureBatch3.JPAAssignment.Model.ToDoList;
import com.FutureBatch3.JPAAssignment.Repository.ToDoRepository;
import com.FutureBatch3.JPAAssignment.Repository.UserRepository;
import com.FutureBatch3.JPAAssignment.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ToDoListController {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{userId}/ToDoList")
    public List<ToDoList> getListByUserId(@PathVariable Long userId){
        return toDoRepository.findByUserId(userId);
    }

    @PostMapping("/user/{userId}/ToDoList")
    public ToDoList addToDoList(@PathVariable Long userId, @Valid @RequestBody ToDoList toDoList){
        return userRepository.findById(userId).map(user -> {
            toDoList.setUser(user);
            return toDoRepository.save(toDoList);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    @PutMapping("/user/{userId}/ToDoList/{toDoId}")
    public ToDoList updateToDoList(@PathVariable Long userId, @PathVariable Long toDoId, @Valid @RequestBody ToDoList toDoRequest){
        if (!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("User not found with id " + userId);
        }

        return toDoRepository.findById(toDoId).map(toDoList -> {
            toDoList.setTitle(toDoRequest.getTitle());
            toDoList.setTdList(toDoRequest.getTdList());
            return toDoRepository.save(toDoList);
        }).orElseThrow(() -> new ResourceNotFoundException("To Do List not found with id " + toDoId));
    }

    @DeleteMapping("/user/{userId}/ToDoList/{toDoId}")
    public ResponseEntity<?> deleteToDoList(@PathVariable Long userId, @PathVariable Long toDoId){
        if (!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("User not found with id " +userId);
        }

        return toDoRepository.findById(toDoId).map(toDoList -> {
           toDoRepository.delete(toDoList);
           return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("To Do List not found with id " + toDoId));
    }
}
