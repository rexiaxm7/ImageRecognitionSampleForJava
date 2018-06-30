package com.example.customvision;

import com.example.customvision.dto.*;
import com.example.customvision.model.ByteArrayFactory;
import com.example.customvision.model.HttpGetFactory;
import com.example.customvision.model.HttpPostFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomVisionService implements ICustomVisionService {

    private static final String URL_GET_TAGS_API = "https://southcentralus.api.cognitive.microsoft.com/customvision/v2.0/Training/projects/{0}/tags";
    private static final String URL_CREATE_TAG_API = "https://southcentralus.api.cognitive.microsoft.com/customvision/v2.0/Training/projects/{0}/tags";
    private static final String URL_GET_ITERATIONS_API = "https://southcentralus.api.cognitive.microsoft.com/customvision/v2.0/Training/projects/{0}/iterations";
    private static final String URL_TRAIN_PROJECT_API = "https://southcentralus.api.cognitive.microsoft.com/customvision/v2.0/Training/projects/{0}/train";
    private static final String URL_PREDICT_IMAGE_API = "https://southcentralus.api.cognitive.microsoft.com/customvision/v2.0/Prediction/{0}/image?iterationId=6e777e23-4ca5-4f79-be62-0b2ac62c027f";

    private final String projectId;
    private final String trainingKey;
    private final String predictionKey;

    public CustomVisionService(String projectId, String trainingKey, String predictionKey) {
        this.projectId = projectId;
        this.trainingKey = trainingKey;
        this.predictionKey = predictionKey;
    }

    @Override
    public List<Tag> getTags() throws URISyntaxException, IOException {

        String url = MessageFormat.format(URL_GET_TAGS_API, projectId);
        HttpGet request = HttpGetFactory.createHttpGet(url);
        request.setHeader("Training-Key", trainingKey);

        HttpClient httpclient = HttpClients.createDefault();
        HttpResponse response = httpclient.execute(request);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            Gson gson = new Gson();
            String string = EntityUtils.toString(entity);
            return gson.fromJson(string, new TypeToken<ArrayList<Tag>>() {
            }.getType());
        }

        return new ArrayList<>();
    }

    @Override
    public List<Iteration> getIterations() throws URISyntaxException, IOException {
        String url = MessageFormat.format(URL_GET_ITERATIONS_API, projectId);
        HttpGet request = HttpGetFactory.createHttpGet(url);
        request.setHeader("Training-Key", trainingKey);

        HttpClient httpclient = HttpClients.createDefault();
        HttpResponse response = httpclient.execute(request);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            Gson gson = new Gson();
            String string = EntityUtils.toString(entity);
            return gson.fromJson(string, new TypeToken<ArrayList<Iteration>>() {
            }.getType());
        }
        return new ArrayList<>();
    }

    @Override
    public Tag createTag(String tagName) throws URISyntaxException, IOException {
        String url = MessageFormat.format(URL_CREATE_TAG_API, projectId);
        HttpPost request = HttpPostFactory.create(url, Arrays.asList(new BasicNameValuePair("name", tagName)));
        request.setHeader("Training-Key", trainingKey);

        HttpClient httpclient = HttpClients.createDefault();
        HttpResponse response = httpclient.execute(request);
        HttpEntity entity = response.getEntity();

        Gson gson = new Gson();
        String string = EntityUtils.toString(entity);
        return gson.fromJson(string, Tag.class);
    }


    @Override
    public ImageCreatedResult createImagesFromData(String imageFilePath) throws IOException, URISyntaxException {
        return createImagesFromData(imageFilePath, new ArrayList<>());
    }

    @Override
    public ImageCreatedResult createImagesFromData(String imageFilePath, List<String> tagIdList) throws URISyntaxException, IOException {
        String url = MessageFormat.format(
                "https://southcentralus.api.cognitive.microsoft.com/customvision/v2.0/Training/projects/{0}/images",
                projectId);
        List<NameValuePair> param = createTagParamList(tagIdList);

        HttpPost request = HttpPostFactory.create(url, param);
        request.setHeader("Content-Type", "application/octet-stream");
        request.setHeader("Training-Key", trainingKey);

        byte[] imageByteArray = ByteArrayFactory.createFromFile(imageFilePath);
        request.setEntity(new ByteArrayEntity(imageByteArray));

        HttpClient httpclient = HttpClients.createDefault();
        HttpResponse response = httpclient.execute(request);
        HttpEntity entity = response.getEntity();

        Gson gson = new Gson();
        String json = EntityUtils.toString(entity);
        return gson.fromJson(json, ImageCreatedResult.class);
    }

    @Override
    public ProjectTrainedResult trainProject() throws URISyntaxException, IOException {
        String url = MessageFormat.format(URL_TRAIN_PROJECT_API, projectId);

        HttpPost request = HttpPostFactory.create(url);
        request.setHeader("Training-Key", trainingKey);

        HttpClient httpclient = HttpClients.createDefault();
        HttpResponse response = httpclient.execute(request);
        HttpEntity entity = response.getEntity();

        Gson gson = new Gson();
        String json = EntityUtils.toString(entity);
        return gson.fromJson(json, ProjectTrainedResult.class);
    }

    @Override
    public ImagePredictedResult predictImage(String imageFilePath) throws URISyntaxException, IOException {

        String url = MessageFormat.format(URL_PREDICT_IMAGE_API, projectId);
        HttpPost request = HttpPostFactory.create(url);
        request.setHeader("Content-Type", "application/octet-stream");
        request.setHeader("Prediction-Key", predictionKey);

        byte[] imageByteArray = ByteArrayFactory.createFromFile(imageFilePath);
        request.setEntity(new ByteArrayEntity(imageByteArray));

        HttpClient httpclient = HttpClients.createDefault();
        HttpResponse response = httpclient.execute(request);
        HttpEntity entity = response.getEntity();
        Gson gson = new Gson();
        String json = EntityUtils.toString(entity);
        return gson.fromJson(json, ImagePredictedResult.class);
    }

    private static List<NameValuePair> createTagParamList(List<String> tagIdList) {
        return tagIdList.stream()
                .map(tagId -> new BasicNameValuePair("tagIds", tagId))
                .collect(Collectors.toList());
    }

}
