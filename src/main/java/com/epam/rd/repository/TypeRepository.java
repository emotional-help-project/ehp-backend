package com.epam.rd.repository;

import com.epam.rd.model.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends BaseRepository<Type> {
}
