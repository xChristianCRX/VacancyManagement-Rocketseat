package com.christian.rocketseat.modules.company.useCases;

import com.christian.rocketseat.exceptions.UserFoundException;
import com.christian.rocketseat.modules.company.entities.CompanyEntity;
import com.christian.rocketseat.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;
    public CompanyEntity execute(CompanyEntity companyEntity){
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        return this.companyRepository.save(companyEntity);
    }
}
