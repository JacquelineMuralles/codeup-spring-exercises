package com.example.springblogapp.repositories;

import com.example.springblogapp.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {
}
