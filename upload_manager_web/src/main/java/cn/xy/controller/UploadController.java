package cn.xy.controller;


import cn.xy.entity.Result;
import cn.xy.service.ApkService;
import cn.xy.util.ApkUtil;
import cn.xy.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 *@Method: 
 *@Description: 
 *@Author: 郑昌蔚
 *@Date: 2020/5/7 0007 13:20
 *@Param: 
 **/
@RestController
public class UploadController {
    @Autowired
    private ApkService apkService;
    @Value("${FILE_SERVER_URL}")
    private String path;
    @RequestMapping("/upload")
    public Result upload(MultipartFile file){
        try {
//            获取文件服务器对象
            FastDFSClient client = new FastDFSClient("classpath:config/fdfs_client.conf");
//            获取文件的文件名
            String filename = file.getOriginalFilename();
//            获取后缀名
            int index = filename
                    .lastIndexOf(".");
//            截取
            String extName = filename.substring(index + 1);
//            上传到文件服务器并返回数据
            String s = client.uploadFile(file.getBytes(), extName);
//            拼接
            String resultStr = path+s;
//            解析安装包名，调用apk工具类
            Map<String, Object> apkMap = ApkUtil.getApkMap(file);
            if (apkMap!=null) {
//            获得图片名称
                String icon = (String) apkMap.get("icon");
//            获取最后一个点的索引
                int lastIndex = icon.lastIndexOf(".");
//            截取
                String extName1 = icon.substring(lastIndex + 1);
//            上传到文件服务器并返回数据
                String s1 = client.uploadFile((byte[]) apkMap.get("twoBarCodes"), extName1);
//            拼接图片路径
                String imgUrl = path + s1;
                apkService.insert(apkMap,resultStr,imgUrl);
                return new Result(true, "上传成功");
            }else {
                return new Result(false,"文件上传失败，请上传正确的apk文件");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"上传失败,文件超过大小或格式不对");
        }
    }




}
