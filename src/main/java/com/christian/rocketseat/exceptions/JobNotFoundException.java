package com.christian.rocketseat.exceptions;

public class JobNotFoundException extends RuntimeException{

    public JobNotFoundException(){
        super("Job not found!");
    }
}
