package cn.xy.util;

import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @ClassName ApkUtil
 * @Description
 * @作者: 郑昌蔚
 * @时间: 2020/5/8 0008 13:30
 **/
public class ApkUtil {
    /**
     *@Method: getApkMap
     *@Description:
     *@Author: 郑昌蔚
     *@Date: 2020/5/8 0008 14:18
     *@Param: [file]
     **/
    public static Map<String ,Object> getApkMap(MultipartFile mFile){
        Map<String ,Object> map =new HashMap<>();
        // 获取文件名
        String fileName = mFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
//        控制是否返回空，默认false
        boolean iTure=false;
        try {
            File file =File.createTempFile(fileName,prefix);
            mFile.transferTo(file);
            if (file.exists() && file.isFile()) {
                iTure =true;
                ApkFile apkFile = new ApkFile(file);
                ApkMeta apkMeta = apkFile.getApkMeta();
                map.put("entryName",apkMeta.getLabel());
                map.put("appName",apkMeta.getPackageName());
                map.put("version",apkMeta.getVersionName());
                map.put("icon",apkMeta.getIcon());
                //  拷贝图标
                byte[] bytes =saveBit(file,apkMeta.getIcon());
                if (bytes ==null){
                    iTure=false;
                }
                map.put("twoBarCodes",bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (iTure){
            return map;
        }else {
            return null;
        }
    }

    private static byte[] saveBit(File file,String icon) throws Exception {
        ZipInputStream zin = null;
        boolean iTf = false;
        //        定义接收的字节数组
        byte[] data = null;
        try {
            //  访问apk 里面的文件
            ZipFile zf = new ZipFile(file);
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            zin = new ZipInputStream(in);
            ZipEntry ze;
            while ((ze = zin.getNextEntry()) != null) {
                if (ze.getName().equals(icon)) {
                    //  拷贝出图标
                    System.out.println("拷贝开始");
                    InputStream inStream = zf.getInputStream(ze);
                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    //创建一个Buffer字符串
                    byte[] buffer = new byte[1024];
                    //每次读取的字符串长度，如果为-1，代表全部读取完毕
                    int len = 0;
                    //使用一个输入流从buffer里把数据读取出来
                    while ((len = inStream.read(buffer)) != -1) {
                        //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                        outStream.write(buffer, 0, len);
                    }
                    //关闭输入流
                    inStream.close();
                    //把outStream里的数据写入内存
                    //得到图片的二进制数据，以二进制封装得到数据，具有通用性
                    data = outStream.toByteArray();
                    iTf = true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            zin.closeEntry();
        }
        if (iTf){
            return data;
        }else {
            return null;
        }
    }
}