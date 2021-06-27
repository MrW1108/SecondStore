package com.example.SecondStore.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class Util {
    public final static String IMAGE_PATH =
            "C:\\Users\\Administrator\\Desktop\\软件开发实践\\img\\";

    public static String SaveImage(MultipartFile file) throws IOException {
        //获取上传文件名,包含后缀
        String originalFilename = file.getOriginalFilename();
        //获取后缀
        assert originalFilename != null;
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        //保存的文件名
        UUID uuid = UUID.randomUUID();
        String dFileName = uuid + substring;
        //生成保存文件
        File uploadFile = new File(IMAGE_PATH + dFileName);
        //将上传文件保存到路径
        file.transferTo(uploadFile);
        return "/img/" + dFileName;
    }
}
