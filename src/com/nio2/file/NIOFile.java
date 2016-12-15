package com.nio2.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author haiswang
 *
 */
public class NIOFile {

    public static void main(String[] args) {
//        oldDeleteFile();
//        newDeleteFile();
        
//        oldRenameFile1();
//        oldRenameFile2();
//        newRenameFile();
        
//        oldFileAttributes();
//        newFileAttributes1();
//        newFileAttributes2();
        
        isFileExist();
    }
    
    /**
     * 判断文件是否存在
     */
    public static void isFileExist() {
        
        Path file = Paths.get("E:\\NIO2.0\\sl-readfile.txt");
        System.out.println("is exists : " + Files.exists(file, LinkOption.NOFOLLOW_LINKS));
        System.out.println("is exists : " + Files.exists(file));
    }
    
    public static void readFile() {
        Path readFile = Paths.get("readfile.txt");
        if(Files.exists(readFile, LinkOption.NOFOLLOW_LINKS)) {
            
        }
        
    }
    
    public static void writeFile() {
        
    }
    
    /**
     * 老的文件删除API
     * 缺陷：如果失败,不会只会返回失败,不会抛出任何异常,不知道为何失败
     */
    public static void oldDeleteFile() {
        File deleteFile = new File("delete.txt");
        boolean isSuccess = deleteFile.delete();
        System.out.println("oldDeleteFile : " + isSuccess);
    }
    
    /**
     * 新版的文件删除API
     * 改进：如果出现删除失败,会throw出来一个异常,告诉我们为何失败
     */
    public static void newDeleteFile() {
        Path deleteFile = Paths.get("delete.txt");
        try {
            Files.delete(deleteFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     *  rename1.txt已经存在,所以这边在rename的时候直接失败
     *  缺陷：rename失败,但是没有任何异常信息
     */
    public static void oldRenameFile1() {
        File beforeRename1 = new File("E:\\NIO2.0\\beforeRename1.txt");
        File rename1 = new File("E:\\NIO2.0\\rename1.txt");
        boolean isSuccess = beforeRename1.renameTo(rename1);
        System.out.println("oldRenameFile1 : " + isSuccess);
    }
    
    /**
     * rename2.txt不存在,所以这边在rename的时候就成功了
     * 
     */
    public static void oldRenameFile2() {
        File beforeRename2 = new File("E:\\NIO2.0\\beforeRename2.txt");
        File rename2 = new File("E:\\NIO2.0\\rename2.txt");
        boolean isSuccess = beforeRename2.renameTo(rename2);
        System.out.println("oldRenameFile2 : " + isSuccess);
    }
    
    /**
     * 这边虽然rename3.txt存在,但是在move的时候设置REPLACE_EXISTING,所以这边就成功了
     */
    public static void newRenameFile() {
        Path beforeRename3 = Paths.get("E:\\NIO2.0\\beforeRename3.txt");
        Path rename3 = Paths.get("E:\\NIO2.0\\rename3.txt");
        try {
            Files.move(beforeRename3, rename3, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 老版获取文件的属性信息方式
     */
    public static void oldFileAttributes() {
        File targetFile = new File("NIOPath.txt");
        System.out.println("isDirectory : " + targetFile.isDirectory());
        System.out.println("isHidden : " + targetFile.isHidden());
        System.out.println("canRead : " + targetFile.canRead());
        System.out.println("canWrite : " + targetFile.canWrite());
        System.out.println("lastModified : " + targetFile.lastModified());
        System.out.println("length : " + targetFile.length());
    }
    
    /**
     * 新版获取文件的属性信息方式,以一个Set的形式返回
     */
    public static void newFileAttributes1() {
        Path path = Paths.get("NIOPath.txt");
        try {
            Map<String, Object> attributes = Files.readAttributes(path, "*", LinkOption.NOFOLLOW_LINKS);
            for (Entry<String, Object> entry : attributes.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 这个是以一个对象的形式返回
     */
    public static void newFileAttributes2() {
        Path path = Paths.get("NIOPath.txt");
        
        try {
            BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
            System.out.println(attributes.isSymbolicLink());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
