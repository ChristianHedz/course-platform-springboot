package com.chris.courseplatform.app.repositories;

import com.chris.courseplatform.app.models.Jwt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtRepository extends JpaRepository<Jwt,Long> {
    Optional<Jwt> findByToken(String jwt);
}


