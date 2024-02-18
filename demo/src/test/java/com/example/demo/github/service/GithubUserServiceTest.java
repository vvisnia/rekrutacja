package com.example.demo.github.service;

import com.example.demo.Utils.Utils;
import com.example.demo.github.model.GithubBranch;
import com.example.demo.github.model.GithubUser;
import com.example.demo.github.model.GithubUserRepository;
import com.example.demo.github.util.GithubResponseException;
import com.example.demo.github.util.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class GithubUserServiceTest {

    @MockBean
    RestTemplate restTemplate;

    @Autowired
    GithubUserService githubUserService;

    @Test
    public void getGithubUserSuccess() {

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET),
                any(), eq(new ParameterizedTypeReference<List<GithubUserRepository>>() {})))
                .thenReturn(ResponseEntity.ok(Utils.createGithubUser().getUserRepositories()));
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET),
                any(), eq(new ParameterizedTypeReference<List<GithubBranch>>() {})))
                .thenReturn(ResponseEntity.ok(Utils.createRepository().getRepositoryBranchList()));


        GithubUser githubUser = githubUserService.getGithubUser("test");

        assertTrue(new ReflectionEquals(githubUser).matches(Utils.createGithubUser()));
    }

    @Test
    public void getGithubUserNotFound() {

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET),
                any(), eq(new ParameterizedTypeReference<List<GithubUserRepository>>() {})))
                .thenThrow(GithubResponseException.class);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET),
                any(), eq(new ParameterizedTypeReference<List<GithubBranch>>() {})))
                .thenReturn(ResponseEntity.ok(Utils.createRepository().getRepositoryBranchList()));

        assertThrows(NotFoundException.class, () -> githubUserService.getGithubUser("test"));
    }

}
