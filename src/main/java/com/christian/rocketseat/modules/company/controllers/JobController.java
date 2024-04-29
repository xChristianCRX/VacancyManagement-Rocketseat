package com.christian.rocketseat.modules.company.controllers;

import com.christian.rocketseat.modules.company.entities.JobEntity;
import com.christian.rocketseat.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    public JobEntity create(@Valid @RequestBody JobEntity jobEntity){
        return this.createJobUseCase.execute(jobEntity);
    }
}
