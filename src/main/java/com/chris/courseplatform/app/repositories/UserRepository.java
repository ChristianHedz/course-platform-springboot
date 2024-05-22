package com.chris.courseplatform.app.repositories;

import com.chris.courseplatform.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String name);
    Optional<User> findById(Long id);
}