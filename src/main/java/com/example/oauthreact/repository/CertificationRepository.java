package com.example.oauthreact.repository;

import com.example.oauthreact.entity.CertificationEntity;
import com.example.oauthreact.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CertificationRepository extends JpaRepository<CertificationEntity,String> {


}
