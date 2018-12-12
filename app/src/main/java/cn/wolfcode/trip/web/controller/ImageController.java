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
    public Map<String,Object> upload(MultipartFile file){
        String url = ImageUploadUtil.uploadFile(file,ImageUploadUtil.DICTIONERY);
        HashMap<String, Object> map = new HashMap<>();
        map.put("url",url);
        map.put("status",1);
        return map;
    }
}
