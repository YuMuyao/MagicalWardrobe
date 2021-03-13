package com.fanxiaoyudemo.magicalwardrobe.Tool;

import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by fanxi on 2020/3/15.
 */

public class SaveImage {
    public static File saveImages(String imageViewsUrl, File saveFile, String fileName) throws IOException {
        InputStream is = null;                      //定义一个输入流。
        BufferedInputStream bis = null;             //定义一个带缓冲的输入流 。
        try {
            URL url = new URL(imageViewsUrl);
            //创建一个URL对象。
            is = url.openStream();                    //打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream。
            bis = new BufferedInputStream(is);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        File extDir = Environment.getExternalStorageDirectory();;
        saveFile=new File(extDir,fileName);
        saveFile.createNewFile();
        saveFile.setWritable(true);
        BufferedOutputStream bos = null;//定义一个带缓冲的输出流。
        try {
            bos = new BufferedOutputStream(new FileOutputStream(saveFile));
            int len = 0;
            byte[] b = new byte[1024];             //创建字节数组。
            while ((len = bis.read(b)) > 0) {
                bos.write(b, 0, len);
            }
        } catch (Exception e) {
            System.out.println("++++++++++++++++++" + e.toString());
        } finally {
            try {
                bos.flush();                 //刷新此缓冲的输出流。
                bis.close();                 //关闭此输入流 。
            } catch (Exception e) {
                System.out.println("++++++++++++++++++" + e.toString());
            }
        }
        return saveFile;
    }
}

