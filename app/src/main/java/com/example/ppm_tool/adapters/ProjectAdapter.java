package com.example.ppm_tool.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ppm_tool.R;
import com.example.ppm_tool.apis.APIClient;
import com.example.ppm_tool.datamodel.OnRefreshViewListener;
import com.example.ppm_tool.model.Project;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    List<Project> projectList;
    private OnRefreshViewListener mOnRefreshViewListener;

    public static class ProjectViewHolder extends RecyclerView.ViewHolder{
        public TextView projectName;
        public TextView projectIdentifier;
        public TextView description;
        public Button deleteButton;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.projectTitleTextView);
            projectIdentifier = itemView.findViewById(R.id.uniqueIdentifierTextView);
            description = itemView.findViewById(R.id.projectDescriptionTextView);
            deleteButton = (Button) itemView.findViewById(R.id.deleteProjectButton);
        }
    }

    public ProjectAdapter(ArrayList<Project> projectList, OnRefreshViewListener onRefreshViewListener){
        this.projectList = projectList;
        this.mOnRefreshViewListener = onRefreshViewListener;
    }
    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent, false);
        ProjectViewHolder pvh = new ProjectViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProjectViewHolder holder, int position) {
        holder.projectIdentifier.setText(projectList.get(position).getProjectIdentifier());
        holder.projectName.setText(projectList.get(position).getProjectName());
        holder.description.setText(projectList.get(position).getDescription());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String deletedItemIdentifier = holder.projectIdentifier.getText().toString();
                Call<Void> deleteByID = APIClient.getProjectService().deletePost(deletedItemIdentifier);
                deleteByID.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(view.getContext(), "Project with ID " + deletedItemIdentifier + " has been deleted", Toast.LENGTH_SHORT).show();
                        mOnRefreshViewListener.refreshView();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }
}
