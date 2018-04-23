package org.yxyqcy.family.common.model;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by lcy on 16/11/25.
 */
public class FileModel implements Serializable{

    private String fileName ;

    private InputStream inputStream;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public FileModel() {
    }

    public FileModel(String fileName, InputStream inputStream) {
        this.fileName = fileName;
        this.inputStream = inputStream;
    }
}
