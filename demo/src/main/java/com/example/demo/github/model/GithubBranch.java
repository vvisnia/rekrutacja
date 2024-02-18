package com.example.demo.github.model;

import com.example.demo.github.util.View;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class GithubBranch {
    @JsonAlias({"name", "branchName"})
    @JsonView(View.Base.class)
    String branchName;
    @JsonAlias({"commit", "lastCommit"})
    @JsonView(View.Base.class)
    Commit lastCommit;
}
