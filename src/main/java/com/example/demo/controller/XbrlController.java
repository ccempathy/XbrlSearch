package com.example.demo.controller;

import com.example.demo.entity.Xbrl;
import com.example.demo.service.XbrlService;
import com.example.demo.util.XbrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2019/8/5 0005.
 */
@RestController
@RequestMapping("/xbrl")
public class XbrlController {
    @Autowired
    private XbrlService xbrlService;
    @Autowired
    private XbrlUtil xbrlUtil;
    @PostMapping("/cc")
    public Object test(@RequestBody Xbrl xbrl) {
        return xbrlService.test(xbrl);
    }

    @GetMapping("/getById")
    public List<Xbrl> getById(Long id) {
        return xbrlService.getById(id);
    }

    @GetMapping ("/test")
    public Object test_xbrl(){
        Xbrl xbrl = new Xbrl();
        xbrl.setXbrlname("xx");
        System.out.println(xbrl.getXbrlname());
        xbrl.setXbrlpath("C:\\Users\\Administrator\\Desktop\\股转公司\\XbrlSearch\\src\\main\\resources\\static\\xbrlfiles\\xx.xml");
        try {
            xbrl.setXbrlparsecontent(xbrlUtil.xbrlParse());

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(xbrl.getXbrlparsecontent());
        return xbrlService.test(xbrl);


    }
}
