package com.za.qa.domain.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.za.qa.domain.analyze.DataAnalyze;
import com.za.qa.hessianbean.CaseDataDTO;

/**
 * @author jujinxin
 * @version 创建时间：2016年11月25日 上午10:15:06 类说明
 */
public class Proprocessor {

    private static Logger logger = LoggerFactory.getLogger(Proprocessor.class);

    public static void doProprocessor(CaseDataDTO casedatadto) {
        String proprocessor = "";
        try {
            proprocessor = casedatadto.getProProcessor();
        } catch (Exception e1) {
            //e1.printStackTrace();
            logger.info("前置处理ProProcessor对象为空，忽略此操作，执行下一步");
        }
        //前置处理ProProcessor
        if (proprocessor.length() > 0) {
            String ProProcessorResult = "";
            try {
                ProProcessorResult = DataAnalyze.analyzeStr(proprocessor);
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info("前置处理ProProcessor解析：\n" + ProProcessorResult);
        }
    }
}
