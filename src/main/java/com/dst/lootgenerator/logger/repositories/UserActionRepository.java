package com.dst.lootgenerator.logger.repositories;

import com.dst.lootgenerator.logger.models.UserAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActionRepository extends JpaRepository<UserAction, Long> {
}
