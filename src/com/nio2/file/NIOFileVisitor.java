package com.nio2.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * NIO目录的遍历
 * @author haiswang
 *
 */
public class NIOFileVisitor implements FileVisitor<Path> {

    
    
    public static void main(String[] args) {
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
            throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc)
            throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc)
            throws IOException {
        // TODO Auto-generated method stub
        return null;
    }
}
