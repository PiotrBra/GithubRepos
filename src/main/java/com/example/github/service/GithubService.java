package com.example.github.service;

import com.example.github.client.GithubClient;
import com.example.github.dto.BranchDto;
import com.example.github.dto.RepositoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubService {
    private final GithubClient githubClient;
    public GithubService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    public List<RepositoryDto> getUserRepositories(String username) {
        List<GithubClient.GitHubRepo> repos = githubClient.getUserRepositories(username);
        // Filtrowanie: usuwamy repozytoria będące forkami
        return repos.stream()
                .filter(repo -> !repo.isFork())
                .map(repo -> {
                    List<BranchDto> branches = githubClient.getRepositoryBranches(
                                    repo.getOwner().getLogin(), repo.getName())
                            .stream()
                            .map(branch -> new BranchDto(branch.getName(), branch.getCommit().getSha()))
                            .collect(Collectors.toList());

                    RepositoryDto repositoryDto = new RepositoryDto();
                    repositoryDto.setName(repo.getName());
                    repositoryDto.setOwnerLogin(repo.getOwner().getLogin());
                    repositoryDto.setBranches(branches);
                    return repositoryDto;
                })
                .collect(Collectors.toList());
    }
}
