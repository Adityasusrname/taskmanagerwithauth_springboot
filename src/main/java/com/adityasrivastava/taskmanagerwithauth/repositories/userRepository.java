package com.adityasrivastava.taskmanagerwithauth.repositories;


import com.adityasrivastava.taskmanagerwithauth.entities.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<userEntity,Long> {
    Optional<userEntity>findByUsername(String username);
}
