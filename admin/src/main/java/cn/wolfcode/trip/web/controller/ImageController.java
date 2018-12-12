package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.base.util.ImageUploadUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/images")
public class ImageController {
    @PostMapping
    public Map<String,Object> upload(MultipartFile upload){
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(upload != null){
            try{
                String url = ImageUploadUtil.uploadFile(upload,ImageUploadUtil.DICTIONERY);
                map.put("url",url);
                map.put("uploaded",1);
            } catch (Exception e){
                map.put("uploaded",0);
                Map<String,Object> temp = new HashMap<String,Object>();
                temp.put("message",e.getMessage());
                map.put("error",temp);
            }

        }
        return map;
    }
}
