package com.anserlt.common.util.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Anserlt
 */
public class XmlUtil {

    /**
     * 从xml格式的字符串中读取制定的值
     * @param xml
     * @return
     */
    public String readFromXmlString(String xml, String key) {
        String value = null;

        Document document = null;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        if (document != null) {
            try {
                Element root = document.getRootElement();
                // fixme 此处可能会报错，与xml文件的父子文件结构有关。？？？？？
                // 获取XML根节点的值。root.element(key)返回的是根节点对象
                value = root.element(key).getText();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return value;
    }

    /**
     * 将xml转化为map
     *
     * <MaskList>
     *      <Mask>
     *          <value>no</value>
     *          <count>0</count>
     *      </Mask>
     *      <Mask>
     *          <value>yes</value>
     *          <count>0</count>
     *      </Mask>
     *      <Mask>
     *          <value>unknown</value>
     *          <count>0</count>
     *      </Mask>
     * </MaskList>
     *
     * return
     * {
     *      "no":"0",
     *      "yes":"0",
     *      "unknown":"0"
     * }
     *
     * @param element 根节点对象
     * @param type 节点名称
     * @return
     */
    public Map<String, String> parseXmlToMap(Element element, String type) {
        if (!ObjectUtils.isEmpty(element)) {
            Map<String, String> paramMap = new HashMap<>();
            // 获取名称为type的所有节点的迭代器
            Iterator<Element> it = element.elementIterator(type);
            while (it.hasNext()) {
                Element e = it.next();
                String key = e.element("value").getText();
                String value = e.element("count").getText();
                paramMap.put(key, value);
            }

            return paramMap;
        }
        return null;
    }

}
