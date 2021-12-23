package com.example.kapitalbankapi.repository;

import com.example.kapitalbankapi.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
