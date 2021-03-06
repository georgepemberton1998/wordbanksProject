package com.example.fundamentalsproject.repo;

import com.example.fundamentalsproject.domain.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {
}
