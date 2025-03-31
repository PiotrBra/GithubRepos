package com.example.github.dto;

public class BranchDto {
    private String name;
    private String lastCommitSha;


    public BranchDto(String name, String lastCommitSha) {
        this.name = name;
        this.lastCommitSha = lastCommitSha;
    }
}
