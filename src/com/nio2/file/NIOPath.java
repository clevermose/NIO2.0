package com.nio2.file;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NIOPath {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Path filePath = Paths.get("NIOPath.txt");
        File nioPathFile = filePath.toFile();
        System.out.println(nioPathFile);
    }

}
