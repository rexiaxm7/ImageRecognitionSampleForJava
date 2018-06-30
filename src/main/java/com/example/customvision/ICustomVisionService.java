package com.example.customvision;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.example.customvision.dto.*;

public interface ICustomVisionService {

	abstract List<Tag> getTags() throws URISyntaxException, IOException;

	List<Iteration> getIterations() throws URISyntaxException, IOException;

	Tag createTag(String tagName) throws URISyntaxException, IOException;

	ImageCreatedResult createImagesFromData(String imageFilePath) throws IOException, URISyntaxException;

	abstract ImageCreatedResult createImagesFromData(String imageFilePath, List<String> tagIdList) throws URISyntaxException, IOException;

	ProjectTrainedResult trainProject() throws URISyntaxException, IOException;

	ImagePredictedResult predictImage(String imageFilePath) throws URISyntaxException, IOException;
}
