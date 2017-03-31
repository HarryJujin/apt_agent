package com.za.qa.domain.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.za.qa.domain.analyze.DataAnalyze;
import com.za.qa.hessianbean.CaseDataDTO;

/**
 * @author jujinxin
 * @version 创建时间：2017年1月10日 下午4:47:33 类说明
 */
public class Postprocessor {

    private static Logger logger = LoggerFactory.getLogger(Postprocessor.class);

    public static void doPostprocessor(CaseDataDTO casedatadto) {
        String postprocessor = "";
        try {
            postprocessor = casedatadto.getPostProcessor();
        } catch (Exception e1) {
            //e1.printStackTrace();
            logger.info("前置处理PostProcessor对象为空，忽略此操作，执行下一步");
        }
        //前置处理ProProcessor
        if (postprocessor.length() > 0) {
            String PostProcessorResult = "";
            try {
                PostProcessorResult = DataAnalyze.analyzeStr(postprocessor);
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info("前置处理PostProcessor解析：\n" + PostProcessorResult);
        }
    }

}
