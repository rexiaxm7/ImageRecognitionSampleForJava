package com.example.customvision.model;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

public class HttpGetFactory {

    public static HttpGet createHttpGet(String url) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(url);
        return new HttpGet(builder.build());
    }
}