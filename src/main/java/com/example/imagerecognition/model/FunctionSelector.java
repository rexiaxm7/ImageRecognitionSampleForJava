package com.example.imagerecognition.model;

import com.example.customvision.CustomVisionService;
import com.example.customvision.utils.ThrowableRunnable;
import com.example.imagerecognition.model.classifying.ClassifyingRunner;
import com.example.imagerecognition.model.learning.LearningRunner;
import com.example.imagerecognition.model.prediction.PredictionRunner;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FunctionSelector {

    private final Map<String, Function> functionMap = new HashMap<>();

    public FunctionSelector(CustomVisionService service) {

        functionMap.put("1",  new Function("予測", new PredictionRunner(service)));
        functionMap.put("2", new Function("学習", new LearningRunner(service)));
        functionMap.put("3", new Function("分類", new ClassifyingRunner(service)));
    }

    public ThrowableRunnable select() {

        while (true) {
            System.out.println("どの機能を実行しますか？");

            // 選択番号と機能名の組み合わせを 選択番号:機能名 のフォーマットで表示
            String functionList = functionMap.entrySet().stream()
                    .map(pair -> MessageFormat.format("{0}:{1}", pair.getKey(), pair.getValue().getName()))
                    .collect(Collectors.joining(" "));
            System.out.println(functionList);

            // 入力を読み取る。
            Scanner scan = new Scanner(System.in);
            String input = scan.next();

            // 選択した場合は機能の処理を返す。
            if (functionMap.containsKey(input)) {
                Function function = functionMap.get(input);
                return function.getRunner();
            }

            System.out.println("無効な入力値です。もう一度入力してください。");
            System.out.println("");
        }
    }


    private class Function{

        private final String name;
        private final ThrowableRunnable runner;

        private Function(String name, ThrowableRunnable runner) {
            this.name = name;
            this.runner = runner;
        }

        public String getName() {
            return name;
        }

        public ThrowableRunnable getRunner() {
            return runner;
        }
    }
}