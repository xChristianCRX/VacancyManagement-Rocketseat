package com.christian.rocketseat.modules.company.useCases;

import com.christian.rocketseat.modules.company.entities.JobEntity;
import com.christian.rocketseat.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity){
        return this.jobRepository.save(jobEntity);
    }
}
