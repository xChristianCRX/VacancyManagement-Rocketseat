package com.christian.rocketseat.modules.candidate.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.christian.rocketseat.modules.candidate.CandidateEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    
    @PostMapping("/")
    public void create(@Valid @RequestBody CandidateEntity candidate){
        System.out.println("Candidato");
        System.out.println(candidate.getEmail());
    }
}
