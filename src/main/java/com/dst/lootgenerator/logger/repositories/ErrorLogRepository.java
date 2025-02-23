package com.dst.lootgenerator.logger.repositories;

import com.dst.lootgenerator.logger.models.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {
}
