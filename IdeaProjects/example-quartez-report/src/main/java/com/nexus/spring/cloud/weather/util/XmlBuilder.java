package com.nexus.spring.cloud.weather.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;

public class XmlBuilder {
    //将xml转为指定的POJO java对象

    public static Object xmlStrToObject(Class<?> clazz, String xmlStr) throws Exception{
        Object xmlObject = null;
        Reader reader = null;
        JAXBContext context = JAXBContext.newInstance(clazz);
        //Xml转为对象的接口
        Unmarshaller unmarshaller =  context.createUnmarshaller();
        reader = new StringReader(xmlStr);
        //传入参数 文件流
        xmlObject = unmarshaller.unmarshal(reader);

        if(null != reader){
            reader.close();;

        }
        return  xmlObject;


    }

}
