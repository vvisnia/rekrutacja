package com.example.demo.github.service;

import com.example.demo.github.config.GithubConfig;
import com.example.demo.github.model.GithubBranch;
import com.example.demo.github.model.GithubUser;
import com.example.demo.github.model.GithubUserRepository;
import com.example.demo.github.util.GithubResponseException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubApiService {

    private final RestTemplate restTemplate;
    private final GithubConfig githubConfig;

    public List<GithubUserRepository> getUserRepositories(String login) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + githubConfig.getToken());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        var url = githubConfig.getUrl() + githubConfig.getGetUsers() + login + githubConfig.getRepos();

        try {
            var githubResponse = restTemplate.exchange(
                    url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<GithubUserRepository>>() {});
            validateGithubRepository(githubResponse);
            return githubResponse.getBody();
        } catch (RestClientException | GithubResponseException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found in github");
        }
    }


    public List<GithubBranch> getUserRepositoriesBranches(String login, String repoName) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + githubConfig.getToken());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        var url = githubConfig.getUrl() + githubConfig.getRepos() + "/" + login + "/" + repoName
                + githubConfig.getBranches();

        try {
            var githubResponse = restTemplate.exchange(
                    url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<GithubBranch>>() {});
            validateGithubBranch(githubResponse);
            return githubResponse.getBody();
        } catch (RestClientException | GithubResponseException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch not found in github");
        }
    }

    private void validateGithubRepository(ResponseEntity<List<GithubUserRepository>> responseEntity) {
        if (responseEntity.getBody() == null) {
            throw new GithubResponseException("No body in response");
        }
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new RestClientException("Invalid status code " + responseEntity.getStatusCode());
        }

    }

    private void validateGithubBranch(ResponseEntity<List<GithubBranch>> responseEntity) {
        if (responseEntity.getBody() == null) {
            throw new GithubResponseException("No body in response");
        }
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new RestClientException("Invalid status code " + responseEntity.getStatusCode());
        }

    }
}
