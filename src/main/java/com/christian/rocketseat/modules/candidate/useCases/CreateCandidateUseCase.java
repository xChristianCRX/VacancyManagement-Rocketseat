package com.christian.rocketseat.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.christian.rocketseat.exceptions.UserFoundException;
import com.christian.rocketseat.modules.candidate.CandidateEntity;
import com.christian.rocketseat.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidate){
        this.candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundException();
        });

        return this.candidateRepository.save(candidate);
    }
}
