package com.example.imagerecognition.model.utils;

import com.example.customvision.dto.ImagePredictedResult;
import com.example.customvision.dto.Prediction;

import java.util.Comparator;
import java.util.Optional;

public class HighestProbabilityPredctionGetter {
    public static Optional<Prediction> get(ImagePredictedResult imagePredictedResult) {
        return imagePredictedResult.getPredictions()
                .stream()
                .sorted(Comparator.comparing(Prediction::getProbability).reversed())
                .findFirst();
    }
}