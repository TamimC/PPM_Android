package com.example.ppm_tool.services;
import com.example.ppm_tool.model.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProjectService {

    @GET("project/all")
    Call<List<Project>> getAllProjects();
}
