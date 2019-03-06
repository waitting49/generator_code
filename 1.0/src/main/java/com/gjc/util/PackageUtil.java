package com.gjc.util;

import java.io.File;

/**
 * 创建文件夹
 * @author hongten(hongtenzone@foxmail.com)
 *
 */
public class PackageUtil {
    public static void main(String[] args) {
        String root = "src/main/java/";
        System.out.println(root);
        File rootFiles = new File(root);
        if(!rootFiles.exists()){
            System.out.println("创建情况："+rootFiles.mkdirs());
        }else{
            System.out.println("存在目录："+rootFiles.getAbsolutePath());
        }
        System.out.println("=================================");
        String myPackage = root + "com/b510/test";
        File myPackageFiles = new File(myPackage);
        if(!myPackageFiles.exists()){
            System.out.println("包路径创建情况："+myPackageFiles.mkdirs());
        }else{
            System.out.println("存在目录："+myPackageFiles.getAbsolutePath());
        }
    }
}