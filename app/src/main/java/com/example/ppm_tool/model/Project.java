package com.example.ppm_tool.model;

import java.util.Date;

public class Project {
    private String projectName;
    private String projectIdentifier;
    private String description;
    private Date startDate;
    private Date endDate;

    public Project(String projectName, String projectIdentifier, String description, Date startDate, Date endDate) {
        this.projectName = projectName;
        this.projectIdentifier = projectIdentifier;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Project(String projectName, String projectIdentifier, String description) {
        this.projectName = projectName;
        this.projectIdentifier = projectIdentifier;
        this.description = description;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectName='" + projectName + '\'' +
                ", projectIdentifier='" + projectIdentifier + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
