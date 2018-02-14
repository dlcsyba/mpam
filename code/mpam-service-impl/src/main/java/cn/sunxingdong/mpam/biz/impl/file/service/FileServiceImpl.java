package cn.sunxingdong.mpam.biz.impl.file.service;

import cn.sunxingdong.mpam.biz.intf.file.service.FileService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Author: sun.xingdong
 * CreateDate: 2018/2/14 10:38
 */
@Service("fileService")
public class FileServiceImpl implements FileService {
    @Override
    public String upload(String path) {
        return null;
    }

    @Override
    public String upload(String path, String storageId) {
        return null;
    }

    @Override
    public String upload(File file) {
        return null;
    }

    @Override
    public String upload(File file, String storageId) {
        return null;
    }

    @Override
    public String upload(InputStream inputStream, String fileName) {
        return null;
    }

    @Override
    public String upload(InputStream inputStream, String fileName, String storageId) {
        return null;
    }

    @Override
    public String download(String key) {
        return null;
    }

    @Override
    public void download(File file, String key) {

    }

    @Override
    public void download(OutputStream outputStream, String key) {

    }
}
