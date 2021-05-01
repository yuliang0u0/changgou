package com.changgou.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.entity.FastDFSFile;
import com.changgou.util.FastDFSUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yuliang0u0
 * @create 2021-04-30 19:18
 */
@RestController
@CrossOrigin
public class FileUploadController {

    /***
     * 文件上传
     * @return
     */
    @PostMapping(value = "/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws Exception {
        //封装一个FastDFSFile
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(), //文件名字
                file.getBytes(),            //文件字节数组
                StringUtils.getFilenameExtension(file.getOriginalFilename()));//文件扩展名

        //文件上传
        String[] uploads = FastDFSUtils.upload(fastDFSFile);
        //组装文件上传地址
//        return FastDFSClient.getTrackerUrl()+"/"+uploads[0]+"/"+uploads[1];
        String url = "http://192.168.211.132:8080/" + uploads[0] + "/" + uploads[1];
        return new Result(true, StatusCode.OK, "上传成功！", url);
    }

}
