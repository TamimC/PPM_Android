package com.example.ppm_tool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ppm_tool.adapters.ProjectAdapter;
import com.example.ppm_tool.apis.APIClient;
import com.example.ppm_tool.datamodel.ActivityAddProject;
import com.example.ppm_tool.datamodel.OnRefreshViewListener;
import com.example.ppm_tool.model.Project;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements OnRefreshViewListener {

    private RecyclerView mRecyclerView;
    private ProjectAdapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    ArrayList<Project> projectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new ProjectAdapter(projectList, this);
        loadAllProjects();
        buildRecyclerView();
    }

    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mlayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mlayoutManager);
    }
    public void onClickAddProject(View view){
        Intent intent = new Intent(MainActivity.this, ActivityAddProject.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
    }

    public void loadAllProjects(){
        projectList.clear();
        Call<List<Project>> projectListResponse = APIClient.getProjectService().getAllProjects();

        projectListResponse.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                if (response.isSuccessful()){
                    for (Project project : response.body()){
                        projectList.add(project);
                    }
                    mRecyclerView.setAdapter(mAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
            }
        });
    }

    @Override
    public void refreshView() {
        loadAllProjects();
        buildRecyclerView();
    }
}