package com.example.imagerecognition.model.classifying;

import com.example.imagerecognition.model.utils.FileExtensionChecker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnclassifiedDirectoryPathInputGetter {

    private UnclassifiedDirectoryPathInputGetter() {
    }

    public static List<Path> get() throws IOException {

        while (true) {
            System.out.println("未分類画像のあるフォルダのパスを入力してください。");
            Scanner scan = new Scanner(System.in);
            String input = scan.next();
            String directoryPath = RemoveEnclosingDoubleQuotes(input);

            try {
                Path path = Paths.get(directoryPath);
                if (Files.exists(path) && Files.isDirectory(path)) {
                    Stream<Path> list = Files.list(path);
                    return list.filter(FileExtensionChecker::isImageFileExtension).collect(Collectors.toList());
                }
            } catch (InvalidPathException e) {
                // 例外が発生しても何も処理しない
            }
            System.out.println("無効なフォルダのパスです。もう一度入力してください。");
            System.out.println();
        }
    }

    private static String RemoveEnclosingDoubleQuotes(String input) {
        return input.charAt(0) == '"' && input.charAt(input.length() - 1) == '"'
                ? input.substring(1, input.length() - 1)
                : input;
    }
}
