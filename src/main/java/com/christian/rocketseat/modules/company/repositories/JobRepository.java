package com.christian.rocketseat.modules.company.repositories;

import com.christian.rocketseat.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
}
