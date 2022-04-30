package com.anserlt.common.util.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author Anserlt
 */
public class XmlUtil {

    /**
     * 从xml格式的字符串中读取制定的值
     * @param xml
     * @return
     */
    public String readFromXml(String xml, String key) {
        String value = null;

        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        if (document != null) {
            try {
                Element root = document.getRootElement();
                // fixme 此处可能会报错，与xml文件的父子文件结构有关。？？？？？
                value = root.element(key).getText();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return value;
    }

}
