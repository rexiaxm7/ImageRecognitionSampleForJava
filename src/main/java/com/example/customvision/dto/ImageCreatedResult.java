
package com.example.customvision.dto;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageCreatedResult {

    @SerializedName("isBatchSuccessful")
    @Expose
    private Boolean isBatchSuccessful;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;

    public Boolean getIsBatchSuccessful() {
        return isBatchSuccessful;
    }

    public void setIsBatchSuccessful(Boolean isBatchSuccessful) {
        this.isBatchSuccessful = isBatchSuccessful;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

}
