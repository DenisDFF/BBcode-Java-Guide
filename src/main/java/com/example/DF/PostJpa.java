package com.example.DF;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostJpa extends JpaRepository<Post, Long> {

}
