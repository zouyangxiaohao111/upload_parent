package cn.xy.service.impl;

import cn.xy.domain.TbApk;
import cn.xy.domain.TbApkItem;
import cn.xy.mapper.TbApkItemMapper;
import cn.xy.mapper.TbApkMapper;
import cn.xy.service.ApkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * ClassName: ApkServiceImpl
 *@Author: 郑昌蔚
 *@Date: 2020/5/7 0007 21:44
 *@Param:
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ApkServiceImpl implements ApkService {
    @Autowired
    private TbApkItemMapper tbApkItemMapper;
    @Autowired
    private TbApkMapper tbApkMapper;


    @Override
    public void insert(Map<String, Object> apkMap, String resultStr, String imgUrl) {
//        建立详细安装包类
        TbApkItem tbApkItem = new TbApkItem();
//        建立安装包大版本类
        TbApk tbApk = new TbApk();
//        应用名称
        tbApk.setEntryName((String) apkMap.get("entryName"));
//        app安装包名称
        tbApk.setAppName((String) apkMap.get("appName"));
//        获取版本的全部名称
        String version =(String) apkMap.get("version");
//        截取大版本并存入数据库中
        tbApk.setVersion(version.substring(0,1));
//        应用名称
        tbApkItem.setEntryName((String) apkMap.get("entryName"));
//        app安装包名称
        tbApkItem.setAppName((String) apkMap.get("appName"));
//        获取版本的全部名称
        tbApkItem.setVersion((String) apkMap.get("version"));
//        放入图片和安装包地址
        tbApkItem.setTwoBarCodes(resultStr+"logo="+imgUrl);
        tbApkItemMapper.insert(tbApkItem);
//        存入外键
        tbApk.setChildId(tbApkItem.getChildId());
        tbApkMapper.insert(tbApk);

    }
}
