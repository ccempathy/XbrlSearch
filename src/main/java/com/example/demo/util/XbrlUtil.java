package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.example.demo.config.Configuration;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/7/29 0029.
 */
@Service
public class XbrlUtil {
    static String path = Configuration.path;

    public static String xbrlParse() throws Exception{
        String json = new String();
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(path));
        Element rootElement = document.getRootElement();
//        System.out.println(rootElement.getName());
        Iterator iterator = rootElement.elementIterator();
        Map<String,Map<String,String>> lastMap= new HashMap<String,Map<String,String>>();
        Map<String,String> contextRefMap = new HashMap<String, String>();
        while (iterator.hasNext()) {
            Element next = (Element) iterator.next();
            String name = next.getName();
//            System.out.println(name);
            if("context".equals(name)){
//                System.out.println(next.asXML());

                String id = getId(next);
//                System.out.println(id);
                contextRefMap.put(id,next.asXML());
                continue;
            }
            if ("unit".equals(name) || "schemaRef".equals(name)) {
                continue;
            }
//            List elements = next.elements();
            if(!next.elements().isEmpty()){
                recursionFlags(next,contextRefMap,lastMap);
//                Attribute idAttribute = next.attribute("id");
//                String id = idAttribute.getValue();
//                for(Object object:elements){
//                    Element childrenElement = (Element) object;
//                    String name1 = childrenElement.getName();
//                    System.out.println(name1);
//                    String date = getDate(childrenElement);
//                    System.out.println(date);
//                    String data = getData(childrenElement);
//                    System.out.println(data);
//                }


            }
            else {
                String contextRef = getContextRef(next);
                Map<String,String> flagMap = new HashMap<String,String>();
//                for(Map.Entry entry:contextRefMap.entrySet()){
//                    if(contextRef.equals(entry.getKey())){
//                        flagMap.put((String) entry.getKey(),(String) entry.getValue());
//                    }
//                }
                flagMap.put(contextRef,contextRefMap.get(contextRef));
//                System.out.println(contextRef);
//                System.out.println(contextRef);
                String data = getData(next);
//                System.out.println(data);
                StringBuilder str = new StringBuilder();
                StringBuilder aa = str.append(next.getName()).append("_").append(contextRef);
                String combineFlag = aa.toString();
                flagMap.put(combineFlag,data);
//                System.out.println(flagMap.get(contextRef)+" "+flagMap.get(combineFlag));
                lastMap.put(combineFlag,flagMap);
                json = JSON.toJSONString(lastMap);
            }
//            for (Map.Entry entry:lastMap.entrySet()){
//                System.out.println(entry.getKey());
//                System.out.println(entry.getValue());
//            }

        }
        return json;

    }
    //获取日期字段
    public static String getContextRef(Element element){
        try{
            Attribute contextAttribute = element.attribute("contextRef");
            String contextRef = contextAttribute.getValue();
//            String date[] = contextRef.split("_");
//            String dateValue = date[date.length-1];
            return contextRef;
        }catch (Exception E){
            return null;
        }
    }
    //获取数据字段
    public static String getData(Element element){
        Object data = element.getData();
        String s = data.toString();
        return s;

    }
    public static String getId(Element element){
        Attribute idAttribute = element.attribute("id");
        String id = idAttribute.getValue();
        return id;
    }
    //递归遍历子节点
    public static void recursionFlags(Element next,Map contextRefMap,Map lastMap){
        if(next.elements().isEmpty()){
            return;
        }
        List elements = next.elements();
        String id = getId(next);
//        Attribute idAttribute = next.attribute("id");
//        String id = idAttribute.getValue();
        for(Object object:elements){
            Element childrenElement = (Element) object;
//            String name1 = childrenElement.getName();
//            System.out.println(name1);
            if(getContextRef(childrenElement) == null){
                recursionFlags(childrenElement,contextRefMap,lastMap);
            }
            else {
                String contextRef = getContextRef(next);
                Map<String,String> flagMap = new HashMap<String,String>();
                flagMap.put(contextRef,(String) contextRefMap.get(contextRef));
                StringBuilder str = new StringBuilder();
                String name1 = childrenElement.getName();
//                System.out.println(name1);
                String data = getData(childrenElement);
//                System.out.println(data);
                StringBuilder str1 = str.append(id).append("_").append(name1).append("_").append(contextRef);
                String combineFlag = str1.toString();
                flagMap.put(combineFlag,data);
//                System.out.println(flagMap.get(contextRef)+" "+flagMap.get(combineFlag));
                lastMap.put(combineFlag,flagMap);
            }
//            recursionFlags(childrenElement);
        }
    }
}
