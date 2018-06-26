package com.example.customvision.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ByteArrayFactory {
    public static byte[] createFromFile(String filePath) throws IOException {

        File file = new File(filePath);
        BufferedImage image = ImageIO.read(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, getExtension(file), byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private static String getExtension(File file){
        String path = file.getPath();
        int index = path.lastIndexOf('.');
        return path.substring(index + 1);
    }
}