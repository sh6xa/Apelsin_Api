package com.example.kapitalbankapi.repository;

import com.example.kapitalbankapi.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail,Integer> {
    List<?>getAllByProduct_Id(Integer product_id);
}
