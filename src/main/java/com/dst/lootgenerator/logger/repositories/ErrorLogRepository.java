package com.dst.lootgenerator.logger.repositories;

import com.dst.lootgenerator.logger.models.ErrorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorLogRepository extends JpaRepository<ErrorData, Long> {
}
