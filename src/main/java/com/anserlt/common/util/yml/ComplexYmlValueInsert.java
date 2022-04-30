package com.anserlt.common.util.yml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 从yml文件中，自动注入复杂的数据结构。
 *
 * 需要添加@Component以及@ConfigurationProperties(prefix = "complex")注解
 *
 * 其中，@ConfigurationProperties(prefix = "complex")中的complex是再yml文件中，value-list的上一级目录的名称
 *
 * 其次，ComplexYmlValueInsert中的 valueList 属性名，需要和对应的需要注入的yml文件中的数据结构的名称报纸一直，大小写英文字母使用“-”来转换
 *
 * 最后，完成注入在使用的时候需要添加@Resource注解，使用的时候需要以类为单位使用，如：
 *      @Resource
 *      ComplexYmlValueInsert value;
 *      ...
 *
 * @author Anserlt
 */
@Component
@ConfigurationProperties(prefix = "complex")
@Data
public class ComplexYmlValueInsert {
    public List<String> valueList;
}
