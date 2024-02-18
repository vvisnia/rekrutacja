package com.example.demo.github.model;

import com.example.demo.github.util.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder(toBuilder = true)
public class GithubUser {
    @JsonView(View.Base.class)
    private String userName;
    @JsonView(View.Base.class)
    private List<GithubUserRepository> userRepositories;
}
