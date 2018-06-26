package com.example.customvision;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.example.customvision.dto.ImageCreatedResult;
import com.example.customvision.dto.Tag;

public interface ICustomVisionService {

	List<Tag> getTags() throws URISyntaxException, IOException;
	ImageCreatedResult createImagesFromData(String imageFilePath, List<String> tagIdList) throws URISyntaxException, IOException;

}
