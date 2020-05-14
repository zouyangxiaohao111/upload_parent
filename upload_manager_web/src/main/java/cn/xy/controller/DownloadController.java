package cn.xy.controller;

import cn.xy.entity.Result;
import cn.xy.util.FastDFSClient;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.util.UUID;

/**
 *@Method: 
 *@Description: 
 *@Author: Administrator
 *@Date: 2020/5/7 0007 15:34
 *@Param: 
 **/
@RestController
@RequestMapping("/down")
public class DownloadController {
    @Value("${FILE_SERVER_URL}")
    private String path;

    @RequestMapping("/download/{id}")
    public Result downLoad(@PathVariable("id") Long id){
        try {
            String url = "http://192.168.25.128/group1/M00/00/00/wKgZgF6YBmmAT4GBAALNqGU3-Bc131.png";

//            获取文件服务器对象
            FastDFSClient client = new FastDFSClient("classpath:config/fdfs_client.conf");
//            获取除去ip地址后剩下的路径
//            String path1="http://192.168.25.128/";
            String urlSub = url.substring(path.length());
//            获得第一个“/”的索引值
            int indexOf = urlSub.indexOf("/");
//            获得组名
            String group = urlSub.substring(0, indexOf);
//            去掉组名后的绝对路径
            String substring = urlSub.substring(indexOf+1);
            //       获取.的索引值
            int index = url.lastIndexOf(".");

//            获取后缀名带.号
            String extname = url.substring(index);
//            从fastDFS下载文件
            byte[] bytes = client.downloadFile(group, substring);
            IOUtils.write(bytes,new FileOutputStream("D:/"+ UUID.randomUUID().toString()+extname));
            System.out.println("下载成功");
            return new Result(true,"下载成功");


        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"下载失败");
        }
    }

}
