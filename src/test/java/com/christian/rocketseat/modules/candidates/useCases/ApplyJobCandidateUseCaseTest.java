package com.christian.rocketseat.modules.candidates.useCases;

import com.christian.rocketseat.exceptions.JobNotFoundException;
import com.christian.rocketseat.exceptions.UserNotFoundException;
import com.christian.rocketseat.modules.candidate.CandidateEntity;
import com.christian.rocketseat.modules.candidate.CandidateRepository;
import com.christian.rocketseat.modules.candidate.useCases.ApplyJobCandidateUseCase;
import com.christian.rocketseat.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void shouldNotBeAbleToApplyJobWithCandidateNotFound(){
        try{
            applyJobCandidateUseCase.execute(null, null);
        }catch (Exception e){
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    public void shouldNotBeAbleToApplyJobWithJobNotFound(){
        var idCandidate = UUID.randomUUID();
        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);
        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try{
            applyJobCandidateUseCase.execute(idCandidate, null);
        }catch (Exception e){
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }
}