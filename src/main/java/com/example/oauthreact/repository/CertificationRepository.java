package com.example.oauthreact.repository;

import com.example.oauthreact.entity.CertificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CertificationRepository extends JpaRepository<CertificationEntity,String> {


    CertificationEntity findByUserId(String userId);
}
