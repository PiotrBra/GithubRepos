package com.example.github.controller;

import com.example.github.dto.RepositoryDto;
import com.example.github.service.GithubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repositories")
public class GithubController {
    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<RepositoryDto>> getUserRepositories(@PathVariable String username) {
        List<RepositoryDto> repositories = githubService.getUserRepositories(username);
        return ResponseEntity.ok(repositories);
    }
}
