package com.anserlt.common.java.util.file;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;

/**
 * @author Anserlt
 */
public class FileUtil {

    /**
     * java对象转为json并保存在文本文件中
     * @param data 对象
     * @param filePath 文件路径
     * @param fileName 文件名
     */
    public void objectToJsonAndSaveToFile(Object data, String filePath, String fileName) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath + File.separator + fileName);
            String content = JSONObject.toJSONString(data);
            writer.write(content);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * byte[]转化为文件
     * @param buf 需要保存的内容转化为byte[]
     * @param filePath 文件路径
     * @param fileName 文件名称
     */
    public void byteArraySaveToFile(byte[] buf, String filePath, String fileName) {
        File file = new File(filePath + File.separator + fileName);
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 本地文件File转为byte[]
     * @param file 本地文件
     * @return bytes
     */
    public byte[] fileToByteArray(File file) {
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * MultipartFile 转换为 byte[]
     * @param file MultipartFile 类型
     * @return byte[]
     */
    public byte[] multipartFileToByteArray(MultipartFile file) {
        byte[] bytes = null;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

}
