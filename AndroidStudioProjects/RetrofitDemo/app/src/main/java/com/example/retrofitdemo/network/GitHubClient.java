package com.example.retrofitdemo.network;

import com.example.retrofitdemo.model.GitHubRepoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubClient {

    @GET("/users/{user}/repos")
    Call<List<GitHubRepoModel>> getRepose(@Path("user") String user);


}
