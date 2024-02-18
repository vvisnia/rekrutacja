package com.example.demo.github.service;

import com.example.demo.github.model.GithubUser;
import com.example.demo.github.util.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GithubUserService {

    private final GithubApiService githubApiService;

    public GithubUser getGithubUser(String login) {
        GithubUser githubUser = GithubUser.builder().userName(login).build();
        try {
            githubUser.setUserRepositories(githubApiService.getUserRepositories(login));
        } catch (ResponseStatusException exception) {
            throw new NotFoundException("User not found for login: " + login + " Details: " + exception);
        }

        githubUser.setUserRepositories(githubUser.getUserRepositories().stream().filter(
                repository -> !repository.getFork()).collect(Collectors.toList()));

        githubUser.getUserRepositories().forEach(
                repository -> {
                        repository.setRepositoryBranchList(githubApiService.getUserRepositoriesBranches(
                                login, repository.getRepositoryName()));
                });

        return githubUser;

    }


}
