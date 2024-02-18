package com.example.demo.github.service;

import com.example.demo.Utils.Utils;
import com.example.demo.github.model.GithubBranch;
import com.example.demo.github.model.GithubUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class GithubApiServiceTest {
    @MockBean
    RestTemplate restTemplate;

    @Autowired
    GithubApiService githubApiService;

    @Test
    public void getGithubRepositoriesSuccess() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET),
                any(), eq(new ParameterizedTypeReference<List<GithubUserRepository>>() {})))
                .thenReturn(ResponseEntity.ok(Utils.createGithubUser().getUserRepositories()));
        List<GithubUserRepository> githubUserRepositoryList = githubApiService.getUserRepositories("test");
        assertTrue(new ReflectionEquals(githubUserRepositoryList).matches(Utils.createGithubUser().getUserRepositories()));
    }

    @Test
    public void githubRepositoryBodyIsNull() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET),
                any(), eq(new ParameterizedTypeReference<List<GithubUserRepository>>() {})))
                .thenReturn(ResponseEntity.ok(null));
        assertThrows(ResponseStatusException.class, () -> githubApiService.getUserRepositories("test"));

    }

    @Test
    public void githubRepositoryResponseStatusError() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET),
                any(), eq(new ParameterizedTypeReference<List<GithubUserRepository>>() {})))
                .thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThrows(ResponseStatusException.class, () -> githubApiService.getUserRepositories("test"));

    }

    @Test
    public void githubRepositoryResponseNot2xx() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET),
                any(), eq(new ParameterizedTypeReference<List<GithubUserRepository>>() {})))
                .thenReturn(ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).body(new ArrayList<GithubUserRepository>() {
                }));
        assertThrows(ResponseStatusException.class, () -> githubApiService.getUserRepositories("test"));
    }

    @Test
    public void getGithubBranchesSuccess() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET),
                any(), eq(new ParameterizedTypeReference<List<GithubBranch>>() {})))
                .thenReturn(ResponseEntity.ok(Utils.createRepository().getRepositoryBranchList()));
        List<GithubBranch> githubBranches = githubApiService.getUserRepositoriesBranches("test", "test");
        assertTrue(new ReflectionEquals(githubBranches).matches(Utils.createRepository().getRepositoryBranchList()));
    }

    @Test
    public void githubBranchesBodyIsNull() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET),
                any(), eq(new ParameterizedTypeReference<List<GithubBranch>>() {})))
                .thenReturn(ResponseEntity.ok(null));
        assertThrows(ResponseStatusException.class, () -> githubApiService.getUserRepositoriesBranches("test", "test"));

    }

    @Test
    public void githubBranchesResponseStatusError() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET),
                any(), eq(new ParameterizedTypeReference<List<GithubBranch>>() {})))
                .thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThrows(ResponseStatusException.class, () -> githubApiService.getUserRepositoriesBranches("test", "test"));

    }

    @Test
    public void githubBranchesResponseNot2xx() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET),
                any(), eq(new ParameterizedTypeReference<List<GithubBranch>>() {})))
                .thenReturn(ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).body(new ArrayList<GithubBranch>() {
                }));
        assertThrows(ResponseStatusException.class, () -> githubApiService.getUserRepositoriesBranches("test", "test"));
    }
}
