package org.yxyqcy.family.common.util;

import java.io.*;

/**
 * Created by lcy on 17/2/17.
 */
public class FileUtil {

    /**
     * 按照编码写文件
     * @param path
     * @param content
     * @param encoding
     */
    public static void write(String path, String content, String encoding)
            {
        File file = new File(path);
        file.delete();
        BufferedWriter writer = null;
        try {
            file.createNewFile();
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), encoding));
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 按照编码读文件
     * @param path
     * @param encoding
     * @return
     */
    public static String read(String path, String encoding){
        String content = "";
        File file = new File(path);
        if(!file.exists())
            return "";
        BufferedReader reader = null;
        String line = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), encoding));
            while ((line = reader.readLine()) != null) {
                content += line + "\n";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return content;
    }

}
