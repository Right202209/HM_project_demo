package org.qqhru.hmpt.controller;

import lombok.extern.slf4j.Slf4j;
import org.qqhru.hmpt.utils.AliOSSUtils;
import org.qqhru.hmpt.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    /**
     * 文件上传
     * @param image 上传的文件
     * @return 文件的访问路径
     */
    @PostMapping("/upload")
    public ResultVo upload(MultipartFile image) throws IOException {
        log.info("文件上传: {}", image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        return ResultVo.success(url);
    }
}
