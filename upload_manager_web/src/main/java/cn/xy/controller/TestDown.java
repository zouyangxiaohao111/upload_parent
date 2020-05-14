package cn.xy.controller;

import cn.xy.util.FastDFSClient;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileOutputStream;
import java.util.UUID;

public class TestDown {
    @Value("${FILE_SERVER_URL}")
    private  String path;
    public static void main(String[] args) {
        try {
            String url ="http://192.168.25.128/group1/M00/00/00/wKgZgF6YBmmAT4GBAALNqGU3-Bc131.png";
//            获取文件服务器对象
            FastDFSClient client = new FastDFSClient("classpath:config/fdfs_client.conf");
//            获取除去ip地址后剩下的路径
            //       获取.的索引值
            int index = url.lastIndexOf(".");


//            获取后缀名带.号
            String extname = url.substring(index);
            String path1 = "http://192.168.25.128/";
            String urlSub = url.substring(path1.length());
//            获得第一个“/”的索引值
            int indexOf = urlSub.indexOf("/");
//            获得组名
            String group = urlSub.substring(0, indexOf);
//            去掉组名后的绝对路径
            String substring = urlSub.substring(indexOf);

//            从fastDFS下载文件
            byte[] bytes = client.downloadFile("group1", "M00/00/00/wKgZgF6YBmmAT4GBAALNqGU3-Bc131.png");
            IOUtils.write(bytes,new FileOutputStream("D:/"+ UUID.randomUUID().toString()+".png"));
            System.out.println("下载成功");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
