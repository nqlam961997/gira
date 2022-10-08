package dev.lam.gira.user.repository;

import dev.lam.gira.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

//    @Query("select (count(u) > 0) from User u where u.email = ?1)")
//    boolean existsByEmail(String email);
//
//    @Query("select (count(u) > 0) from User u where u.username = ?1)")
//    boolean existsByUsername(String username);
}
