package com.pyrosandro.bds.repository;

import com.pyrosandro.bds.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserIdentifier(String userIdentifier);
}
