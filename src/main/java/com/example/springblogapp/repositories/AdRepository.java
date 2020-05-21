package com.example.springblogapp.repositories;

import com.example.springblogapp.model.Ad;
import com.example.springblogapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {
    Ad findByTitle(String title);
}
