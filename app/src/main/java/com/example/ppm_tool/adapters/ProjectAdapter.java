package com.example.ppm_tool.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ppm_tool.R;
import com.example.ppm_tool.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    List<Project> projectList;

    public static class ProjectViewHolder extends RecyclerView.ViewHolder{
        public TextView projectName;
        public TextView projectIdentifier;
        public TextView description;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.projectTitleTextView);
            projectIdentifier = itemView.findViewById(R.id.uniqueIdentifierTextView);
            description = itemView.findViewById(R.id.projectDescriptionTextView);
        }
    }

    public ProjectAdapter(ArrayList<Project> projectList){
        this.projectList = projectList;
    }
    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent, false);
        ProjectViewHolder pvh = new ProjectViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.projectIdentifier.setText(projectList.get(position).getProjectIdentifier());
        holder.projectName.setText(projectList.get(position).getProjectName());
        holder.description.setText(projectList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }
}
