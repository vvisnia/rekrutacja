package com.example.demo.github.model;

import com.example.demo.github.util.View;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class Commit {
    @JsonView({View.Base.class})
    private String sha;
}
