package com.imarkov.parking.repo;

import com.imarkov.parking.model.dao.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<Boolean> existsByUsername(String username);
}
