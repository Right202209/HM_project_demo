package org.qqhru.hmpt.controller;

import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.qqhru.hmpt.utils.AliOSSUtils;
import org.qqhru.hmpt.utils.MinIOUtils;
import org.qqhru.hmpt.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {
//    本地
//    @PostMapping("/upload")
//    public ResultVo upload(String username, Integer age, MultipartFile image) throws IOException {
//        log.info("文件上传：{},{},{}",username,age,image);
//
//        //获取原始文件名
//        String originalFilename = image.getOriginalFilename();
//
//        //构建新的文件名
//        String extname = originalFilename.substring(originalFilename.lastIndexOf("."));//文件扩展名
//        String newFileName = UUID.randomUUID().toString()+extname;//随机名+文件扩展名
//
//        //将文件存储在服务器的磁盘目录
//        image.transferTo(new File("D:\\TSET\\ShiXun\\HMPT\\files/"+newFileName));
//
//        return ResultVo.success("http://localhost:8080/"+newFileName);
//    }
    @Autowired
    private AliOSSUtils aliOSSUtils;
    /**
     * spring mvc专门提供操作文件上传的对象 MultipartFile
     * @param image
     * @return
     */
    @PostMapping("/upload")
    public ResultVo upload(MultipartFile image) throws IOException {
        //阿里云的代码
        String url = aliOSSUtils.upload(image);
        return ResultVo.success( url );
    }
//    @Autowired
//    private MinIOUtils minIOUtils; // 注意这里使用 MinIOUtils
//
//    @PostMapping("/upload")
//    public ResultVo upload(MultipartFile image) throws IOException {
//        String url = minIOUtils.upload(image);
//        System.err.println(url);
//        return ResultVo.success(url);
//    }
}