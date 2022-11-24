package com.epam.rd.repository;

import com.epam.rd.model.entity.User;
import com.epam.rd.model.enumerations.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);

    Page<User> findAllPaginated(Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.gender = :gender")
    Page<User> findAllByGenderPaginated(Gender gender, Pageable pageable);

}
