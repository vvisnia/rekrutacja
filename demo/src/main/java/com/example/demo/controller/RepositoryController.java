package com.example.demo.controller;

import com.example.demo.github.model.GithubUser;
import com.example.demo.github.service.GithubUserService;
import com.example.demo.github.util.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RepositoryController {

    private final GithubUserService githubUserService;


    //sprawdz postmanem czy inne przechodza
   // @GetMapping(value = "/repos/commits/{login}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/repos/commits/{login}")
    @JsonView(View.Base.class)
    public ResponseEntity<GithubUser> getUserInfo(@PathVariable String login) throws Exception {
        return  ResponseEntity.ok(githubUserService.getGithubUser(login));
    }
}
