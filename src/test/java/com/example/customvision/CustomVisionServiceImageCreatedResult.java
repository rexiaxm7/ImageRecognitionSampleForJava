package com.example.customvision;


import com.example.customvision.dto.*;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CustomVisionServiceImageCreatedResult {

	// Replace your projectId
	private static final String projectId = "5dc0dd8f-ed53-4f83-903a-646a2a910142";
	// Replace your training key.
	private static final String trainingKey = "a7af806f421c472485e1402f003e7363";
	// Replace your prediction key.
	private static final String predictionKey = "6af793ccad9748b980f06840fad79e57";

	@Test
    public void getTagsTest() {
        CustomVisionService service = new CustomVisionService(projectId, trainingKey, predictionKey);
        try{
            List<Tag> tagList = service.getTags();
            Assert.assertNotEquals(null, tagList);
        }catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void createTagTest() {
        CustomVisionService service = new CustomVisionService(projectId, trainingKey, predictionKey);
        try{
            Tag tag = service.createTag("Doraemon");
            Assert.assertNotEquals(null, tag);
        }catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void getIterationsTest() {
        CustomVisionService service = new CustomVisionService(projectId, trainingKey, predictionKey);
        try{
            List<Iteration> IterationList = service.getIterations();
            Assert.assertNotEquals(null, IterationList);
        }catch (Exception e){
            Assert.fail();
        }
    }

	@Test
	public void createImagesFromDataTest() {
		CustomVisionService service = new CustomVisionService(projectId, trainingKey, predictionKey);
		try {
            ImageCreatedResult imageCreatedResult = service.createImagesFromData("C:\\Users\\RexiA7\\Desktop\\qaS1IXyw_bigger.jpg");
            Assert.assertNotEquals(null, imageCreatedResult);
        }catch (Exception e){
		    Assert.fail();
        }
	}

    @Test
    public void trainProjectTest() {
        CustomVisionService service = new CustomVisionService(projectId, trainingKey, predictionKey);
        try{
            ProjectTrainedResult projectTrainedResult = service.trainProject();
            Assert.assertNotEquals(null, projectTrainedResult);
        }catch (Exception e){
            Assert.fail();
        }
    }

	@Test
	public void predictImageTest(){
        CustomVisionService service = new CustomVisionService(projectId, trainingKey, predictionKey);
        try {
            ImagePredictedResult imagePredictedResult = service.predictImage("C:\\Users\\RexiA7\\Desktop\\qaS1IXyw_bigger.jpg");
            Assert.assertNotEquals(null, imagePredictedResult);
        }catch (Exception e){
            Assert.fail();
        }
    }
}
