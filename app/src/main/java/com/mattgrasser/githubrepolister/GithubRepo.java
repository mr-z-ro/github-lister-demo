package com.mattgrasser.githubrepolister;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 8/15/15.
 */
public class GithubRepo {
    long id;
    String ownerLogin;
    String ownerUrl;
    String name;
    String fullName;
    String description;
    boolean isPrivate;
    boolean canFork;
    String htmlUrl;
    String cloneUrl;
    String gitUrl;
    String createdAt;
    String updatedAt;

    public GithubRepo(JSONObject githubRepoJson) throws JSONException {
        this.id = githubRepoJson.getLong("id");
        JSONObject ownerJsonObj = githubRepoJson.getJSONObject("owner");
        this.ownerLogin = ownerJsonObj.getString("login");
        this.ownerUrl = ownerJsonObj.getString("url");
        this.name = githubRepoJson.getString("name");
        this.fullName = githubRepoJson.getString("full_name");
        this.description = githubRepoJson.getString("description");
        this.isPrivate = githubRepoJson.getBoolean("private");
        this.canFork = githubRepoJson.getBoolean("fork");
        this.htmlUrl = githubRepoJson.getString("html_url");
        this.cloneUrl = githubRepoJson.getString("clone_url");
        this.gitUrl = githubRepoJson.getString("git_url");
        this.createdAt = githubRepoJson.getString("created_at");
        this.updatedAt = githubRepoJson.getString("updated_at");
    }

    public static List<GithubRepo> createList(JSONArray githubRepoJsonArray) {
        List<GithubRepo> list = new ArrayList<GithubRepo>();
        try {
            for (int i = 0; i < githubRepoJsonArray.length(); i++) {
                list.add(new GithubRepo(githubRepoJsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            Log.e("GithubRepo", e.getMessage());
        }
        return list;
    }
}
