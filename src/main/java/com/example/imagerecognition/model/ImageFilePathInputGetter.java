package com.example.imagerecognition.model;

import java.nio.file.*;
import java.util.Scanner;

public class ImageFilePathInputGetter {

    private ImageFilePathInputGetter() {
    }

    public static String get() {

        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{jpg,jpeg,png,bmp}");
        while (true) {
            System.out.println("画像のファイルパスを入力してください。");
            Scanner scan = new Scanner(System.in);
            String input = scan.next();
            String imageFilePath = RemoveEnclosingDoubleQuotes(input);


            try {
                Path path = Paths.get(imageFilePath);
                if (Files.exists(path) && pathMatcher.matches(path)) return imageFilePath;
            } catch (InvalidPathException e) {
                // 例外が発生しても何も処理しない
            }
            System.out.println("無効なファイルパスか拡張子が異なります。もう一度入力してください。");
            System.out.println();
        }
    }

    private static String RemoveEnclosingDoubleQuotes(String input) {
        return input.charAt(0) == '"' && input.charAt(input.length() - 1) == '"'
                ? input.substring(1, input.length() - 1)
                : input;
    }
}