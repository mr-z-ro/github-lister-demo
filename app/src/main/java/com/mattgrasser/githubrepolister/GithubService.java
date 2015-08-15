package com.mattgrasser.githubrepolister;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by matt on 8/15/15.
 */
public interface GithubService {
    @GET("/users/{user}/repos")
    List<GithubRepo> listRepos(@Path("user") String user);
}
