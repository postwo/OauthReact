package com.example.oauthreact.repository;

import com.example.oauthreact.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity,String> {

    UserEntity findByUserId(String userId);

    boolean existsByUserId(String userId);

}
