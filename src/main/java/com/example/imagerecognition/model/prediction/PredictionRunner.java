package com.example.imagerecognition.model.prediction;

import com.example.customvision.CustomVisionService;
import com.example.customvision.ICustomVisionService;
import com.example.customvision.dto.ImagePredictedResult;
import com.example.customvision.dto.Prediction;
import com.example.customvision.dto.Tag;
import com.example.customvision.utils.ExceptionUtils;
import com.example.customvision.utils.ThrowableRunnable;
import com.example.imagerecognition.model.utils.HighestProbabilityPredictionGetter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class PredictionRunner implements ThrowableRunnable {

    private final ICustomVisionService service;

    public PredictionRunner(CustomVisionService service) {
        this.service = service;
    }

    @Override
    public void run() throws Exception {

        // 画像のパスを取得
        String imageFilePath = ImageFilePathInputGetter.get();
        System.out.println();

        // 予測を実行
        ImagePredictedResult imagePredictedResult = service.predictImage(imageFilePath);

        // もっとも確率の高い予測を抽出
        Optional<Prediction> optionalPrediction = HighestProbabilityPredictionGetter.get(imagePredictedResult);

        // 予測結果がなければ、処理に失敗してるので中断
        if (!optionalPrediction.isPresent()) {
            System.out.println("予測に失敗しました。");
            return;
        }

        // 予測をメッセージにして表示
        Prediction prediction = optionalPrediction.get();
        System.out.println(PredictionMessageFactory.create(prediction));
        String answer = YesNoInputGetter.get();

        // 予測に成功していたら、予測を終了する。
        if (answer.equals("yes")) {
            System.out.println("予測に成功しました。");
            System.out.println("予測を終了します。");
            return;
        }

        // 予想に失敗していた場合、改善を図る
        improvePrediction(imageFilePath);
    }

    private void improvePrediction(String imageFilePath) throws URISyntaxException, IOException {

        // 正しい分類を入力させる。
        System.out.println("この画像の正しい分類を教えてください。");
        String tagName = new Scanner(System.in).next();

        // 現在登録されているTagを取得し、入力された分類と同じTagNameがあるかを確認する。
        List<Tag> tagList = service.getTags();
        Optional<Tag> optionalTag = getTagWithSameName(tagList, tagName);

        // 同じ名前のTagがあれば、そのTag。なければ新しいTagを作って、予測した画像に対してTagを貼って登録を行う。
        Tag tag = optionalTag.orElseGet(ExceptionUtils.toUnchecked(() -> service.createTag(tagName)));
        service.createImagesFromData(imageFilePath, Arrays.asList(tag.getId()));
        System.out.println("登録しました。次回学習時に適用されます。");

    }

    private Optional<Tag> getTagWithSameName(List<Tag> tagList, String tagName) {
        return tagList.stream()
                .filter(tag -> tag.getName().equals(tagName))
                .findFirst();
    }

}
