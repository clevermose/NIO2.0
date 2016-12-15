package com.nio2.file;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Iterator;

/**
 * NIO2.0的文件系统
 * @author haiswang
 *
 */
public class NIOFileSystem {

    /**
     * @param args
     */
    public static void main(String[] args) {
        FileSystem fs = FileSystems.getDefault();
        
        Iterable<FileStore> fileStores = fs.getFileStores();
        
        Iterator<FileStore> fsIter = null;
        if(null!=fileStores) {
            fsIter = fileStores.iterator();
        }
        
        if(null==fsIter) {
            return;
        }
        
        while(fsIter.hasNext()) {
            FileStore fileStore = fsIter.next();
            System.out.println(fileStore.name());
        }
        
    }

}
