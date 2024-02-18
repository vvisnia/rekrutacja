package com.example.demo.Utils;

import com.example.demo.github.model.Commit;
import com.example.demo.github.model.GithubBranch;
import com.example.demo.github.model.GithubUser;
import com.example.demo.github.model.GithubUserRepository;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static GithubUser createGithubUser() {
        List<GithubUserRepository> userRepository = new ArrayList<GithubUserRepository>();
        userRepository.add(Utils.createRepository());
        return GithubUser.builder()
                .userName("test")
                .userRepositories(
                        userRepository
                )
                .build();
    }

    public static GithubUserRepository createRepository() {
        List<GithubBranch> githubBranches = new ArrayList<GithubBranch>();
        githubBranches.add(Utils.createBranch());
        return GithubUserRepository.builder()
                .fork(false)
                .repositoryName("test")
                .repositoryBranchList(githubBranches)
                .build();
    }

    public static GithubBranch createBranch() {
        Commit commit = new Commit();
        commit.setSha("test");
        return GithubBranch.builder()
                .branchName("test")
                .lastCommit(commit)
                .build();
    }

}
