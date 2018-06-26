package com.example.imagerecognition;

import com.example.customvision.dto.Prediction;

import java.text.MessageFormat;

public class PredictionMessageFactory {

    private static final String MESSAGE_ALMOST_PREDICTION = "この画像はきっと{0}ですね！({1})";
    private static final String MESSAGE_PROBABLY_PREDICTION = "この画像は{0}でしょうか？({1})";
    private static final String MESSAGE_MAYBE_PREDICTION = "この画像はたぶん{0}ですよね？({1})";
    private static final String MESSAGE_PERHAPS_PREDICTION = "この画像は{0}かもしれません。({1})";

    private PredictionMessageFactory() {
    }

    public static String create(Prediction prediction) {
        Double probability = prediction.getProbability();
        String tagName = prediction.getTagName();
        String predictionMessage = getPredictionMessage(probability);
        return MessageFormat.format(predictionMessage, tagName, probability * 100);
    }

    private static String getPredictionMessage(double probability){
        if (probability >= 0.9) return MESSAGE_ALMOST_PREDICTION;
        if (probability >= 0.6) return MESSAGE_PROBABLY_PREDICTION;
        if (probability >= 0.3) return MESSAGE_MAYBE_PREDICTION;
        return MESSAGE_PERHAPS_PREDICTION;
    }
}