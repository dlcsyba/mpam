package cn.sunxingdong.mpam.biz.intf.file.service;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Author: sun.xingdong
 * CreateDate: 2018/2/14 10:36
 */
public interface FileService {

    String upload(String path);

    String upload(String path, String storageId);

    String upload(File file);

    String upload(File file, String storageId);

    String upload(InputStream inputStream, String fileName);

    String upload(InputStream inputStream, String fileName, String storageId);

    String download(String key);

    void download(File file, String key);

    void download(OutputStream outputStream, String key);
}
