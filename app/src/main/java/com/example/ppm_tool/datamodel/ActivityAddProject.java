package com.example.ppm_tool.datamodel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ppm_tool.MainActivity;
import com.example.ppm_tool.R;
import com.example.ppm_tool.apis.APIClient;
import com.example.ppm_tool.helper.HelperMethods;
import com.example.ppm_tool.model.Project;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAddProject extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_add);
    }

    public void onClickBackButton(View view){
        Intent intent = new Intent(ActivityAddProject.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
    }
    public void onClickSubmitNewProject(View view) {
        EditText projectNameET = (EditText) findViewById(R.id.projectNameInput);
        String projectName = projectNameET.getText().toString();

        EditText projectIdentifierET = (EditText) findViewById(R.id.projectIdentifierInput);
        String projectIdentifier = projectIdentifierET.getText().toString();

        EditText projectDescriptionET = (EditText) findViewById(R.id.projectDescriptionInput);
        String projectDescription = projectDescriptionET.getText().toString();

        String projectStartDate = HelperMethods.getDateFromDatePicker((DatePicker) findViewById(R.id.startDateInput));
        String projectEndDate = HelperMethods.getDateFromDatePicker((DatePicker) findViewById(R.id.endDateInput));

        Project newProject = new Project(projectName, projectIdentifier, projectDescription, projectStartDate, projectEndDate);
        Call<Project> call = APIClient.getProjectService().createProject(newProject);
        call.enqueue(new Callback<Project>() {

            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(ActivityAddProject.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
                } else{
                    try {
                        String errors = response.errorBody().string();
                        JsonObject convertedObject = new Gson().fromJson(errors, JsonObject.class);
                        if (convertedObject.get("description") != null && convertedObject.get("description").getAsString() != null)
                            Toast.makeText(getApplicationContext(),convertedObject.get("description").getAsString(), Toast.LENGTH_SHORT).show();
                        if (convertedObject.get("projectIdentifier") != null && convertedObject.get("projectIdentifier").getAsString() != null)
                            Toast.makeText(getApplicationContext(),convertedObject.get("projectIdentifier").getAsString(), Toast.LENGTH_SHORT).show();
                        if (convertedObject.get("projectName") != null && convertedObject.get("projectName").getAsString() != null)
                            Toast.makeText(getApplicationContext(),convertedObject.get("projectName").getAsString(), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Log.e("ERROR", "JSON ERROR");
                    }
                }
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
            }
        });
    }
}
