package org.activiti.model;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by lcy on 17/6/11.
 */
public class WorkflowBpmnModel implements Serializable {
    private static final long serialVersionUID = 6787306810929875397L;

    private byte[] contents;

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public WorkflowBpmnModel() {
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    public WorkflowBpmnModel(byte[] contents, String fileName) {
        this.contents = contents;
        this.fileName = fileName;
    }

    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public WorkflowBpmnModel(InputStream inputStream,String fileName) {
        this.inputStream = inputStream;
        this.fileName = fileName;
    }
}
