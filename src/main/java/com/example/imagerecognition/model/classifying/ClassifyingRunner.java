package com.example.imagerecognition.model.classifying;

import com.example.customvision.CustomVisionService;
import com.example.customvision.ICustomVisionService;
import com.example.customvision.dto.ImagePredictedResult;
import com.example.customvision.dto.Prediction;
import com.example.customvision.utils.ThrowableRunnable;
import com.example.imagerecognition.model.utils.HighestProbabilityPredictionGetter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class ClassifyingRunner implements ThrowableRunnable {

    private final ICustomVisionService service;

    public ClassifyingRunner(CustomVisionService service) {
        this.service = service;
    }

    @Override
    public void run() throws Exception {

        List<Path> paths = UnclassifiedDirectoryPathInputGetter.get();
        if(paths.size() == 0){
            System.out.println("指定されたフォルダに画像ファイルがありませんでした。");
            return;
        }
        System.out.println("画像の分類を開始します。(0.5未満はUnknownと判断されます。)");

        for (Path path: paths) {
            ImagePredictedResult imagePredictedResult = service.predictImage(path.toString());
            Optional<Prediction> optionalPrediction = HighestProbabilityPredictionGetter.get(imagePredictedResult);
            if(!optionalPrediction.isPresent()){
                System.out.println("予測に失敗しました。");
                return;
            }

            Prediction prediction = optionalPrediction.get();
            String directoryName = prediction.getProbability() >= 0.5 ? prediction.getTagName() : "Unknown";
            Path classifiedDirectory = path.getParent().resolve(directoryName) ;
            if(Files.exists(classifiedDirectory)){
                Files.move(path, classifiedDirectory.resolve(path.getFileName()));
            }else{
                Files.createDirectory(classifiedDirectory);
                Files.move(path, classifiedDirectory.resolve(path.getFileName()));
            }
        }
        System.out.println("画像の分類が完了しました。");
    }
}
