package cn.xy.controller;

import cn.xy.service.ApkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Description:
 *@Author: 郑昌蔚
 *@Date: 2020/5/7 0007 21:55
 *@Param:
 **/
@RequestMapping(value = "/apk",method = {RequestMethod.GET,RequestMethod.POST})
@RestController
public class ApkController {
    @Autowired
    private ApkService apkService;



}
