package com.example.demo.github.model;

import com.example.demo.github.util.View;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class GithubUserRepository {
    @JsonAlias({"name", "repositoryName"})
    @JsonView(View.Base.class)
    private String repositoryName;
    @JsonView(View.Base.class)
    private List<GithubBranch> repositoryBranchList;
    private Boolean fork;
}
