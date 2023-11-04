package com.secureqna.secureqna.repositories;

import com.secureqna.secureqna.objects.UserSqna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserSqna,Long> {

    Optional<UserSqna> findByUsername(String name);

    boolean existsByUsername(String username);

}
