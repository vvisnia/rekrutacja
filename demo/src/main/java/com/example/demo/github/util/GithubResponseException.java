package com.example.demo.github.util;

public class GithubResponseException extends RuntimeException{

    public GithubResponseException(String message) {
        super(message);
    }
}
