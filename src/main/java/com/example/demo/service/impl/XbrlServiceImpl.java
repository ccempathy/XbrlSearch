package com.example.demo.service.impl;

import com.example.demo.entity.Xbrl;
import com.example.demo.repository.XbrlRepository;
import com.example.demo.service.XbrlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/8/5 0005.
 */
@Service
public class XbrlServiceImpl implements XbrlService {

    @Resource
    private XbrlRepository xbrlRepository;
//    @Resource
//    private XbrlUtil xbrlUtil;
    @Override
    public Object test(Xbrl xbrl) {
        return xbrlRepository.saveAndFlush(xbrl);
    }

    @Override
    public List<Xbrl> getById(Long id) {
        return xbrlRepository.getById(id);
    }

//    public Object test_xbrl() {
//        Xbrl xbrl = new Xbrl();
//        xbrl.setId((long)3);
//        xbrl.setXbrlname("xx");
//        xbrl.setXbrlpath("C:\\Users\\Administrator\\Desktop\\股转公司\\XbrlSearch\\src\\main\\resources\\static\\xbrlfiles\\xx.xml");
//        try {
//            xbrl.setXbrlparsecontent("name:dh");
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println(xbrl.getXbrlparsecontent());
//        Object object = xbrlRepository.getById((long)1);
//        System.out.println(object.toString());
//        return object;
//
//    }
//
//    public static void main(String[] args) {
//        System.out.println(new XbrlServiceImpl().test_xbrl());
//    }

}
