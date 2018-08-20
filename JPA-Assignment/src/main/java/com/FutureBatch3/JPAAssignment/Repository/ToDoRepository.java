package com.FutureBatch3.JPAAssignment.Repository;


import com.FutureBatch3.JPAAssignment.Model.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository <ToDoList, Long> {
    List<ToDoList> findByUserId(Long userId);
}
