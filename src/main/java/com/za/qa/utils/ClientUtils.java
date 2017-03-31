package com.za.qa.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.za.qa.hessianbean.CaseDataDTO;

public class ClientUtils {

    private static Logger logger = LoggerFactory.getLogger(ClientUtils.class);

    public ClientUtils() {

    }

    public static void caseStartLog(CaseDataDTO casedatadto) {
        String type = casedatadto.getApi_type();
        String caseNo = casedatadto.getCaseNo();
        logger.info("---------" + "当前用例执行开始，用例编号:" + caseNo + ",接口类型: " + type);
    }

    public static void caseEndLog(CaseDataDTO casedatadto) {
        String caseNo = casedatadto.getCaseNo();
        logger.info("---------" + "当前用例执行完毕，用例编号:" + caseNo + "---------");
    }

    public static JSONObject Str2JsonObject(String bizcontent) {
        Map<String, String> businessParamsMap = Utilities.serializeFormat(bizcontent);
        JSONObject reqJson = new JSONObject();
        for (String key : businessParamsMap.keySet()) {
            reqJson.put(key, businessParamsMap.get(key));
        }
        logger.info("Excel参数JSON: " + reqJson);
        return reqJson;
    }

}
