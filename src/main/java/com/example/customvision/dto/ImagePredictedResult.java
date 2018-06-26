package com.example.customvision.dto;

import java.util.List;

import com.example.customvision.dto.Prediction;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImagePredictedResult {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("project")
    @Expose
    private String project;
    @SerializedName("iteration")
    @Expose
    private String iteration;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("predictions")
    @Expose
    private List<Prediction> predictions = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getIteration() {
        return iteration;
    }

    public void setIteration(String iteration) {
        this.iteration = iteration;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

}