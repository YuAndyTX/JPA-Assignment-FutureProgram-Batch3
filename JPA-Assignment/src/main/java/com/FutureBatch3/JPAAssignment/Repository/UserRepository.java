package com.FutureBatch3.JPAAssignment.Repository;

import com.FutureBatch3.JPAAssignment.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
