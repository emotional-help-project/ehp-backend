package com.epam.rd.repository;

import com.epam.rd.model.entity.User;
import com.epam.rd.model.enumerations.URole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByEmail(String username);
    public User findByResetPasswordToken(String token);
    int countUsersByRoleLike(URole role);
//    @Query("SELECT count(role) FROM users where role like'ADMIN'")
//    String findAdminRole();

}
