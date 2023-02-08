package com.anserlt.common.java.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 读取excel数据
 *
 * Listener 不能被spring管理，要每次读取excel都要new,然后里面用到spring管理的对象可以通过构造方法传进去
 *
 * @author Anserlt
 */
@Slf4j
public class ExcelListener extends AnalysisEventListener<ExcelModel> {

    private Object someSpringBeans;

    private Object someParam;

    /**
     * 所需要的参数、bean可以通过构造函数传进来。
     * @param someSpringBeans 如：某个service
     * @param someParam 如：String abc
     */
    public ExcelListener(Object someSpringBeans, Object someParam) {
        this.someSpringBeans = someSpringBeans;
        this.someParam = someParam;
    }

    /**
     * 按行来进行数据处理，每次读取一行
     * @param data 一行数据
     * @param context fixme 暂时不知道是干嘛用的
     */
    @Override
    public void invoke(ExcelModel data, AnalysisContext context) {
        log.info((String) someSpringBeans);
        log.info((String) someParam);
    }

    /**
     * 所有数据处理完成后执行的操作
     * @param context fixme 暂时不知道是干嘛用的
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info((String) someSpringBeans);
        log.info((String) someParam);
    }
}
