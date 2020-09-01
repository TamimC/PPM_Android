package com.example.ppm_tool.services;
import com.example.ppm_tool.model.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProjectService {

    @GET("project/all")
    Call<List<Project>> getAllProjects();

    @POST("project")
    Call<Project> createProject(@Body Project project);

    @DELETE("project/{projectIdentifier}")
    Call<Void> deletePost(@Path("projectIdentifier") String projectIdentifier);
}
