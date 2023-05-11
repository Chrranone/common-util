package com.anserlt.common.java.util.zip;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    /**
     * 导出文件到zip
     */
    public void export(HttpServletResponse response) {
        HashMap<String, byte[]> fileMap = new HashMap<>();

        // 文件的byte数组
        byte[] bytes = new byte[0];
        fileMap.put("文件名.后缀名", bytes);

        ZipOutputStream zipOut = null;
        try {
            zipOut = new ZipOutputStream(response.getOutputStream());
            for (Map.Entry<String, byte[]> file : fileMap.entrySet()) {
                ZipEntry zipEntry = new ZipEntry(file.getKey());

                zipOut.putNextEntry(zipEntry);
                zipOut.write(file.getValue(), 0, file.getValue().length);
                zipOut.closeEntry();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (zipOut != null) {
                    zipOut.finish();
                    zipOut.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        response.setContentType("application/force-download");
        response.setStatus(HttpServletResponse.SC_OK);

        String zipFileName =  "文件名.zip";
        response.addHeader("Content-Disposition", "attachment; filename=\"" + zipFileName + "\"");
    }

}
