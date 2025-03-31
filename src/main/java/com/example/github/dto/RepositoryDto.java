package com.example.github.dto;

import java.util.List;

public class RepositoryDto {
    private String name;
    private String ownerLogin;
    private List<BranchDto> branches;

    public RepositoryDto() {
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }
    public void setBranches(List<BranchDto> branches) {
        this.branches = branches;
    }
}
