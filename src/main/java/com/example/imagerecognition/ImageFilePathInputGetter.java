package com.example.imagerecognition;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ImageFilePathInputGetter {

    private ImageFilePathInputGetter() {
    }

    public static String get() {
        while (true) {
            System.out.println("画像のファイルパスを入力してください。");
            Scanner scan = new Scanner(System.in);
            String imageFilePath = scan.next();

            try {
                Path path = Paths.get(imageFilePath);
                if (Files.exists(path)) return imageFilePath;
            } catch (InvalidPathException e) {
                // 例外が発生しても何も処理しない
            }
            System.out.println("無効なファイルパスです。もう一度入力してください。");
            System.out.println();
        }
    }
}