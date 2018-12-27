package com.alan.springboothelloworld.common.util;

import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    /**
     * 上传文件
     * @param file
     * @throws IOException
     */
    public static void fileupload(MultipartFile file) throws IOException {

        // 存放在这个路径下：该路径是该工程目录下的static文件下：(注：该文件可能需要自己创建)
        // 放在static下的原因是，存放的是静态文件资源，即通过浏览器输入本地服务器地址，加文件名时是可以访问到的
        String uploadUrl = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/";

        //如果目录不存在，则自动创建文件夹
        File dir = new File(uploadUrl);
        if (!dir.exists()){
            dir.mkdir();
        }

        // 保存文件
        String filename = file.getName();
        File serverFile = new File(uploadUrl + filename);
        file.transferTo(serverFile);
    }

    /**
     * 采用二进制流的方式保存文件
     * @param file
     * @param filePath
     * @param fileName
     * @throws IOException
     */
    public static void fileupload(byte[] file,String filePath,String fileName) throws IOException {
        File targetfile = new File(filePath);
        if(targetfile.exists()) {
            targetfile.mkdirs();
        }

        //二进制流写入
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static void executeUploadFile(MultipartFile file) throws IOException {
        // 获取文件名称,包含后缀
        String fileName = file.getOriginalFilename();

        // 存放在这个路径下：该路径是该工程目录下的static文件下：(注：该文件可能需要自己创建)
        // 放在static下的原因是，存放的是静态文件资源，即通过浏览器输入本地服务器地址，加文件名时是可以访问到的
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/";
        FileUtil.fileupload(file.getBytes(), fileName, path);
    }
}
