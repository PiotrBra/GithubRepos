package com.example.github.client;

import com.example.github.exception.RateLimitExceededException;
import com.example.github.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class GithubClient {

    private final RestTemplate restTemplate;
    private final String githubApiUrl;

    public GithubClient(RestTemplate restTemplate, @Value("${github.api.url}") String githubApiUrl) {
        this.restTemplate = restTemplate;
        this.githubApiUrl = githubApiUrl;
    }

    public List<GitHubRepo> getUserRepositories(String username) {
        String url = githubApiUrl + "/users/" + username + "/repos";
        try {
            ResponseEntity<GitHubRepo[]> response = restTemplate.getForEntity(url, GitHubRepo[].class);
            return Arrays.asList(response.getBody());
        } catch (HttpClientErrorException.Forbidden ex) {
            throw new RateLimitExceededException("API rate limit exceeded. " + ex.getResponseBodyAsString());
        } catch (HttpClientErrorException.NotFound ex) {
            throw new UserNotFoundException("User not found");
        }
    }

    public List<GitHubBranch> getRepositoryBranches(String owner, String repoName) {
        String url = githubApiUrl + "/repos/" + owner + "/" + repoName + "/branches";
        ResponseEntity<GitHubBranch[]> response = restTemplate.getForEntity(url, GitHubBranch[].class);
        return Arrays.asList(response.getBody());
    }

    // Modele wewnętrzne odzwierciedlające odpowiedź API GitHub
    public static class GitHubRepo {
        private String name;
        private Owner owner;
        private boolean fork;

        public GitHubRepo() {
        }
        public String getName() {
            return name;
        }

        public Owner getOwner() {
            return owner;
        }

        public boolean isFork() {
            return fork;
        }

        public static class Owner {
            private String login;

            public Owner() {
            }

            public String getLogin() {
                return login;
            }

        }
    }

    public static class GitHubBranch {
        private String name;
        private Commit commit;

        public GitHubBranch() {
        }

        public String getName() {
            return name;
        }
        public Commit getCommit() {
            return commit;
        }

        public static class Commit {
            private String sha;

            public Commit() {
            }
            public String getSha() {
                return sha;
            }

        }
    }
}
