package cn.wolfcode.trip.base.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class ImageUploadUtil {
    public static String DICTIONERY = "J:/KDL/javaweb/5.陈慧-11.30/骡窝窝";
    /**
     *处理文件上传
     * @param file 文件内容
     * @param fileDictionery 文件所在磁盘目录
     */
    public static String uploadFile(MultipartFile file,String fileDictionery){
       String uuid = UUID.randomUUID().toString();
        String fileName = "";
        try {
        int b = 0;
        byte[] by = new byte[1024];
        String name = file.getOriginalFilename();
        fileName ="/upload/" + uuid+"."+FilenameUtils.getExtension(name);
        InputStream fileInputStream = file.getInputStream();
        File f = new File(fileDictionery,fileName);
        OutputStream out = new FileOutputStream(f);
        while((b=fileInputStream.read(by))!=-1){
            out.write(by,0,b);
        }
        fileInputStream.close();
        out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }



    /**
     * 处理文件上传
     * @param file
     * @param basePath
     * @return  123.png
     */
   /* public static String upload(MultipartFile file, String basePath) {
        String uuid = UUID.randomUUID().toString();

        String orgFileName = file.getOriginalFilename();
        String ext= "." + FilenameUtils.getExtension(orgFileName);
        String fileName = uuid + ext;
        try {
            File targetFile = new File(basePath, fileName);
            FileUtils.writeByteArrayToFile(targetFile, file.getBytes());

            return "/upload/" + fileName;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }*/
}
