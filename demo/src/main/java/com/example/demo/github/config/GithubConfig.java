package com.example.demo.github.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "github")
@Data
@Configuration
public class GithubConfig {
    private String token;
    private String url;
    private String getUsers;
    private String repos;
    private String branches;
}
