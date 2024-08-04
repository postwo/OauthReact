package com.example.oauthreact.repository;

import com.example.oauthreact.entity.CertificationEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CertificationRepository extends JpaRepository<CertificationEntity,String> {


    CertificationEntity findByUserId(String userId);

    @Transactional
    void deleteByUserId(String userId);
}
