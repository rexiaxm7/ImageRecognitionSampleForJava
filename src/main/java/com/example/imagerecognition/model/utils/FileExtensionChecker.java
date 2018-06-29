package com.example.imagerecognition.model.utils;


import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

public class FileExtensionChecker {
    private FileExtensionChecker() {
    }

    public static boolean isImageFileExtension(Path path){
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{jpg,jpeg,png,bmp}");
        return pathMatcher.matches(path);
    }
}
