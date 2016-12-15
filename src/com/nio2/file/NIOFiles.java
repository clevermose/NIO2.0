package com.nio2.file;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NIOFiles {

    /**
     * @param args
     */
    public static void main(String[] args) {
        isFileExist();
    }
    
    public static void isFileExist() {
        Path file = Paths.get("/home/NIO/sl-readfile.txt");
        
        System.out.println("is exist : " + Files.exists(file, LinkOption.NOFOLLOW_LINKS));
        System.out.println("is exist : " + Files.exists(file));
        
    }

}
