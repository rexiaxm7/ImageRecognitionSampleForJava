package com.example.imagerecognition;

import com.example.customvision.CustomVisionService;
import com.example.customvision.utils.ThrowableRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    // Replace your projectId
    private static final String projectId = "";
    // Replace your training key.
    private static final String trainingKey = "";
    // Replace your prediction key.
    private static final String predictionKey = "";

    public static void main(String[] args) throws Exception {

        // 機能を選択する
        ThrowableRunnable functionRunner = selectFunction();
        // 選択した機能を実行する。
        functionRunner.run();

    }

    public static Map<String, ThrowableRunnable> createFunctionRunnerMap() {
        return new HashMap<String, ThrowableRunnable>() {
            {
                put("1", new PredictionRunner(new CustomVisionService(projectId, trainingKey, predictionKey)));
                put("2", new LearningRunner());
            }
        };
    }

    private static ThrowableRunnable selectFunction() {
        Map<String, ThrowableRunnable> functionRunnerMap = createFunctionRunnerMap();
        while (true) {
            System.out.println("どの機能を実行しますか？");
            System.out.println("1:認識 2:学習");
            Scanner scan = new Scanner(System.in);
            String input = scan.next();
            if (functionRunnerMap.containsKey(input)) return functionRunnerMap.get(input);

            System.out.println("無効な入力値です。もう一度入力してください。");
            System.out.println("");
        }
    }

}
