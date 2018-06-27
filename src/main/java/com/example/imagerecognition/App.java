package com.example.imagerecognition;

import com.example.customvision.CustomVisionService;
import com.example.customvision.utils.ThrowableRunnable;
import com.example.imagerecognition.model.FunctionSelector;

public class App {

    // Replace your projectId
    private static final String projectId = "";
    // Replace your training key.
    private static final String trainingKey = "";
    // Replace your prediction key.
    private static final String predictionKey = "";

    public static void main(String[] args) throws Exception {

        if(projectId.isEmpty() || trainingKey.isEmpty() || predictionKey.isEmpty()){
            System.out.println("APIのIdまたはKeyの設定がありません。");
            return;
        }

        CustomVisionService service = new CustomVisionService(projectId, trainingKey, predictionKey);
        FunctionSelector functionSelector = new FunctionSelector(service);

        // 機能を選択する
        ThrowableRunnable functionRunner = functionSelector.select();
        // 選択した機能を実行する。
        functionRunner.run();

    }

}
