package com.example.customvision.model;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class HttpPostFactory {

    private HttpPostFactory() {
    }

    public static HttpPost create(String url) throws URISyntaxException {
        return create(url, new ArrayList<>());
    }
    public static HttpPost create(String url, List<NameValuePair> nameValuePair) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(url);
        builder.setParameters(nameValuePair);
        return new HttpPost(builder.build());
    }
}