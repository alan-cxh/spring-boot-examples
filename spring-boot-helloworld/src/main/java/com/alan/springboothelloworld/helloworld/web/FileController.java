package com.alan.springboothelloworld.helloworld.web;

import com.alan.springboothelloworld.common.util.FileUtil;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 1.上传单文件 方法一，二
 * 2.上传多文件 方法同上
 * 3.压缩文件
 */
@RestController
public class FileController {

    /**
     * 单个文件上传
     * 使用MultipartFile对象内置的方法transferTo()就可以实现JSP页面上传到request内的文件对象直接存储到指定文件File对象内
     * @param file
     * @return
     */
    @RequestMapping("file/upload")
    public String upload(@RequestParam MultipartFile file){
        try {
            FileUtil.fileupload(file);
        }catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }

    //方法二
    @RequestMapping("file/upload1")
    public String upload1(@RequestParam MultipartFile file){
        try {
            // 获取文件名称,包含后缀
            String fileName = file.getOriginalFilename();

            // 存放在这个路径下：该路径是该工程目录下的static文件下：(注：该文件可能需要自己创建)
            // 放在static下的原因是，存放的是静态文件资源，即通过浏览器输入本地服务器地址，加文件名时是可以访问到的
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/";
            FileUtil.fileupload(file.getBytes(), path, fileName);
        }catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }

    /**
     * 多文件上传
     * @param files
     * @return
     */
    @RequestMapping("file/uploadMultiFile")
    public String uploadMultiFile(@RequestParam MultipartFile[] files){
        try {
            for (int i=0; i<files.length; i++) {
                FileUtil.executeUploadFile(files[i]);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }


    /**
     * 压缩文件
     * @throws IOException
     */
    @RequestMapping("/file/zipFile")
    public void zipFile() throws IOException {
        //这个是文件夹的绝对路径，如果想要相对路径就自行了解写法
        String sourceFile = "D:\\alan\\java\\workspace\\spring-boot-examples\\spring-boot-helloworld\\target\\classes\\test";
        //这个是压缩之后的文件绝对路径
        FileOutputStream fos = new FileOutputStream(
                "D:\\alan\\java\\workspace\\spring-boot-examples\\spring-boot-helloworld\\target\\classes\\result");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(sourceFile);

        zipFile(fileToZip, fileToZip.getName(), zipOut);
        zipOut.close();
        fos.close();
    }

    private void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }


}
