package com.example.demo.service;

import com.example.demo.entity.Xbrl;

import java.util.List;

/**
 * Created by Administrator on 2019/8/5 0005.
 */
public interface XbrlService {

    Object test(Xbrl xbrl);

    List<Xbrl> getById(Long id);

}
