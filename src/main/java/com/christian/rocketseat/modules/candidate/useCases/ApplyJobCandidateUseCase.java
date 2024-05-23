package com.christian.rocketseat.modules.candidate.useCases;

import com.christian.rocketseat.exceptions.JobNotFoundException;
import com.christian.rocketseat.exceptions.UserNotFoundException;
import com.christian.rocketseat.modules.candidate.CandidateRepository;
import com.christian.rocketseat.modules.candidate.repository.ApplyJobRepository;
import com.christian.rocketseat.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public void execute(UUID idCandidate, UUID idJob){
        this.candidateRepository.findById(idCandidate).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        this.jobRepository.findById(idJob).orElseThrow(() -> {
            throw new JobNotFoundException();
        });


    }
}
