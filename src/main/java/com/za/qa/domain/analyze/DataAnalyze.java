package com.za.qa.domain.analyze;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.za.qa.domain.enums.EnvType;
import com.za.qa.domain.verify.ResponseVerify;
import com.za.qa.hessianbean.CaseDataDTO;
import com.za.qa.keywords.ExpressionEngine;
import com.za.qa.keywords.ExpressionRegister;
import com.za.qa.keywords.KeywordDefinition;
import com.za.qa.utils.ClientUtils;
import com.za.qa.utils.Utilities;

public class DataAnalyze {

    private static Logger logger = LoggerFactory.getLogger(DataAnalyze.class);

    public static String getAnalyzeData(CaseDataDTO casedataDTO) {
        logger.info("\n表达式引擎解析前的参数: " + "\n" + casedataDTO.getData());
        String bizcontent = "";
        try {
            bizcontent = analyzefirst(casedataDTO);
            bizcontent = analyzeStr(bizcontent, casedataDTO);
            bizcontent = analyzeCurrent(bizcontent, casedataDTO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("Excel参数: " + "表达式引擎解析失败");
            e.printStackTrace();
        }
        logger.info("\n表达式引擎解析后的参数: " + "\n" + bizcontent);
        return bizcontent;
    }

    public static String analyzefirst(CaseDataDTO casedataDTO) throws Exception {
        //处理$开头的关键字
        // **主动加载所有定义的关键字注解,如果有新的keyword 类需要加载，则需要将keyword对象加入到数组*//*
        String bizcontent = casedataDTO.getData();
        Object[] keywords = { new KeywordDefinition() };
        ExpressionEngine engine = new ExpressionEngine(keywords);
        // **解析表达式，并进行数据组装*//*
        String MARK = "MYMARKBEGIN";

        String regx = "\\$([@a-zA-Z=_'\\s\u4e00-\u9fa5\\\\(\\\\),#0-9.%\\\\\\\"\\/:\\-]*[\"][\\)])";// 过滤表达式
        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(bizcontent);
        while (m.find()) {
            logger.info("表达式: [" + m.group(1) + "]开始解析...");
            String replaceRegx = "$" + m.group(1);
            bizcontent = bizcontent.replace(replaceRegx, MARK);
            String replaceRegxNew = replaceParameter(replaceRegx, "ParaReplace", casedataDTO);
            bizcontent = bizcontent.replace(MARK, replaceRegxNew);
        }
        return bizcontent;
    }

    public static String analyzeStr(CaseDataDTO casedataDTO) throws Exception {
        String bizcontent = casedataDTO.getData().trim();
        // **主动加载所有定义的关键字注解,如果有新的keyword 类需要加载，则需要将keyword对象加入到数组*//*
        Object[] keywords = { new KeywordDefinition() };
        ExpressionEngine engine = new ExpressionEngine(keywords);
        // **解析表达式，并进行数据组装*//*
        String MARK = "MYMARK";

        //String regx1 = "@([a-zA-Z\u4e00-\u9fa5\\\\(\\\\),0-9\u4e00-\u9fa5\\\\\\\"]*[\\)])";// 过滤表达式
        String regx = "@([\\+a-zA-Z\\[\\]=_'\\s\u4e00-\u9fa5\\\\(\\\\),#0-9.\\\\\\\"\\/:\\-]*[\\)])";

        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(bizcontent);
        while (m.find()) {
            logger.info("表达式: [" + m.group(1) + "]开始解析...");
            String replaceRegx = "@" + m.group(1);
            String replaceRegxNew = m.group(1).toString();
            bizcontent = bizcontent.replace(replaceRegx, MARK);
            /*
             * 此处需要替换表达式，由于String.replaceFrist在替换表达式时碰到问题，所以替换成自定义算法 具体实现为先将json
             * 串中相同的表达式全部替换成MARK,然后再获取表达式的值进行替换
             */
            while (bizcontent.indexOf(MARK) != -1) {
                String keywordExp = replaceRegxNew;
                try {
                    String result="";
					try {
						result = (String) engine.execute(keywordExp);
					} catch (Exception e) {
						// TODO Auto-generated catch block
                        logger.error(String.format("表达式: %s 解析获取到的值为空值,放弃执行后续步骤,请检查", keywordExp));
					}
                    if (!result.equals("")) {
                        bizcontent = bizcontent.replaceFirst(MARK, result);
                    } else {
                        bizcontent = bizcontent.replaceFirst(MARK, result);
                        /*
                         * throw new Exception(
                         * String.format("表达式: %s 解析获取到的值为空值,放弃执行后续步骤,请检查"
                         * ,keywordExp));
                         */
                    }

                } catch (com.googlecode.aviator.exception.CompileExpressionErrorException e) {

                    throw new Exception(String.format("非法表达式:[%s],未定义，解析数据失败，忽略后续测试", keywordExp));
                } catch (com.googlecode.aviator.exception.ExpressionRuntimeException e) {
                    throw new Exception(String.format("非法表达式:[%s],未定义，解析数据失败，忽略后续测试", keywordExp));
                } catch (com.googlecode.aviator.exception.ExpressionSyntaxErrorException e) {
                    throw new Exception(String.format("非法表达式:[%s],未定义，解析数据失败，忽略后续测试", keywordExp));
                }
            }
        }
        return bizcontent;
    }

    /**
     * @param bizcontent 方法说明:第二步解析@开头的关键字
     */
    public static String analyzeStr(String bizcontent) throws Exception {

        // **主动加载所有定义的关键字注解,如果有新的keyword 类需要加载，则需要将keyword对象加入到数组*//*
        Object[] keywords = { new KeywordDefinition() };
        ExpressionEngine engine = new ExpressionEngine(keywords);
        // **解析表达式，并进行数据组装*//*
        String MARK = "MYMARK";

        //String regx1 = "@([a-zA-Z\u4e00-\u9fa5\\\\(\\\\),0-9\u4e00-\u9fa5\\\\\\\"]*[\\)])";// 过滤表达式
        String regx = "@([\\+a-zA-Z\\[\\]=_'\\s\u4e00-\u9fa5\\\\(\\\\),#0-9.\\\\\\\"\\/:\\-]*[\\)])";

        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(bizcontent);
        while (m.find()) {
            logger.info("表达式: [" + m.group(1) + "]开始解析...");
            String replaceRegx = "@" + m.group(1);
            String replaceRegxNew = m.group(1).toString();
            bizcontent = bizcontent.replace(replaceRegx, MARK);
            /*
             * 此处需要替换表达式，由于String.replaceFrist在替换表达式时碰到问题，所以替换成自定义算法 具体实现为先将json
             * 串中相同的表达式全部替换成MARK,然后再获取表达式的值进行替换
             */
            while (bizcontent.indexOf(MARK) != -1) {
                String keywordExp = replaceRegxNew;
                try {
                    String result="";
					try {
						result = (String) engine.execute(keywordExp);
					} catch (Exception e) {
						// TODO Auto-generated catch block
                        logger.error(String.format("表达式: %s 解析获取到的值为空值,放弃执行后续步骤,请检查", keywordExp));
					}
                    if (!result.equals("")) {
                        bizcontent = bizcontent.replaceFirst(MARK, result);
                    } else {
                        bizcontent = bizcontent.replaceFirst(MARK, result);
                        /*
                         * throw new Exception(
                         * String.format("表达式: %s 解析获取到的值为空值,放弃执行后续步骤,请检查"
                         * ,keywordExp));
                         */
                    }

                } catch (com.googlecode.aviator.exception.CompileExpressionErrorException e) {

                    throw new Exception(String.format("非法表达式:[%s],未定义，解析数据失败，忽略后续测试", keywordExp));
                } catch (com.googlecode.aviator.exception.ExpressionRuntimeException e) {
                    throw new Exception(String.format("非法表达式:[%s],未定义，解析数据失败，忽略后续测试", keywordExp));
                } catch (com.googlecode.aviator.exception.ExpressionSyntaxErrorException e) {
                    throw new Exception(String.format("非法表达式:[%s],未定义，解析数据失败，忽略后续测试", keywordExp));
                }
            }
        }
        return bizcontent;
    }

    public static String analyzeStr(String bizcontent, CaseDataDTO casedataDTO) throws Exception {

        // **主动加载所有定义的关键字注解,如果有新的keyword 类需要加载，则需要将keyword对象加入到数组*//*
        Object[] keywords = { new KeywordDefinition() };
        ExpressionEngine engine = new ExpressionEngine(keywords);
        // **解析表达式，并进行数据组装*//*
        String MARK = "MYMARK";

        //String regx1 = "@([a-zA-Z\u4e00-\u9fa5\\\\(\\\\),0-9\u4e00-\u9fa5\\\\\\\"]*[\\)])";// 过滤表达式
        String regx = "@([\\+a-zA-Z\\[\\]=_'\\s\u4e00-\u9fa5\\\\(\\\\),#0-9.\\\\\\\"\\/:\\-]*[\\)])";
        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(bizcontent);
        while (m.find()) {
            logger.info("表达式: [" + m.group(1) + "]开始解析...");
            String replaceRegx = "@" + m.group(1);
            String replaceRegxNew = m.group(1).toString();
            bizcontent = bizcontent.replace(replaceRegx, MARK);
            replaceRegxNew = replaceParameter(replaceRegxNew, "ResponseDepend", casedataDTO);
            replaceRegxNew = replaceParameter(replaceRegxNew, "PayloadDepend", casedataDTO);
            replaceRegxNew = replaceParameter(replaceRegxNew, "PayloadDependMulti", casedataDTO);
            replaceRegxNew = replaceParameter(replaceRegxNew, "ResponseDependMulti", casedataDTO);
            replaceRegxNew = replaceParameter(replaceRegxNew, "PayloadCurrent", casedataDTO);
            replaceRegxNew = replaceParameter(replaceRegxNew, "ResponseCurrent", casedataDTO);
            replaceRegxNew = replaceSqlQueryDepend(replaceRegxNew, "SqlQueryDepend", casedataDTO);
            replaceRegxNew = replacePayloadDependCurrent(replaceRegxNew, "PayloadDependCurrent", bizcontent,
                    casedataDTO);
            replaceRegxNew = replaceSubString(replaceRegxNew, "RsubStr", bizcontent);
            replaceRegxNew = repalceIdb(replaceRegxNew, "RIdb", casedataDTO);

            /*
             * 此处需要替换表达式，由于String.replaceFrist在替换表达式时碰到问题，所以替换成自定义算法 具体实现为先将json
             * 串中相同的表达式全部替换成MARK,然后再获取表达式的值进行替换
             */
            while (bizcontent.indexOf(MARK) != -1) {
                String keywordExp = replaceRegxNew;
                try {
                    String result="";
					try {
						result = (String) engine.execute(keywordExp);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.error(String.format("表达式: %s 解析获取到的值为空值,放弃执行后续步骤,请检查", keywordExp));
					}
                    if (!result.equals("")) {
                        bizcontent = bizcontent.replaceFirst(MARK, result);
                    } else {
                        ;
                        //bizcontent = bizcontent.replaceFirst(MARK, result);
                        if (replaceRegxNew.contains("Rreplace")) {
                            bizcontent = bizcontent.replaceFirst(MARK, "");
                        } else {
                            bizcontent = bizcontent.replaceFirst(MARK, replaceRegx);
                        }
                        /*
                         * throw new Exception(
                         * String.format("表达式: %s 解析获取到的值为空值,放弃执行后续步骤,请检查"
                         * ,keywordExp));
                         */
                    }

                } catch (com.googlecode.aviator.exception.CompileExpressionErrorException e) {
                    bizcontent = bizcontent.replaceFirst(MARK, replaceRegx);
                    throw new Exception(String.format("非法表达式:[%s],未定义，解析数据失败，忽略后续测试", keywordExp));
                } catch (com.googlecode.aviator.exception.ExpressionRuntimeException e) {
                    bizcontent = bizcontent.replaceFirst(MARK, replaceRegx);
                    throw new Exception(String.format("非法表达式:[%s],未定义，解析数据失败，忽略后续测试", keywordExp));
                } catch (com.googlecode.aviator.exception.ExpressionSyntaxErrorException e) {
                    bizcontent = bizcontent.replaceFirst(MARK, replaceRegx);
                    throw new Exception(String.format("非法表达式:[%s],未定义，解析数据失败，忽略后续测试", keywordExp));
                }
            }
        }
        return bizcontent;
    }

    /**
     * @param bizcontent 方法说明:第三步解析&开头的关键字（解析本身依赖）
     */
    public static String analyzeCurrent(String bizcontent, CaseDataDTO casedataDTO) throws Exception {

        // **主动加载所有定义的关键字注解,如果有新的keyword 类需要加载，则需要将keyword对象加入到数组*//*
        Object[] keywords = { new KeywordDefinition() };
        ExpressionEngine engine = new ExpressionEngine(keywords);
        // **解析表达式，并进行数据组装*//*
        String MARK = "MYMARK";

        String regx = "&([a-zA-Z=_'\\s\u4e00-\u9fa5\\\\(\\\\),0-9.\\\\\\\"\\/:\\-]*[\\)])";
        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(bizcontent);
        while (m.find()) {
            logger.info("表达式: [" + m.group(1) + "]开始解析...");
            String replaceRegx = "&" + m.group(1);
            bizcontent = bizcontent.replace(replaceRegx, MARK);
            String replaceRegxNew = m.group(1).toString();
            replaceRegxNew = replaceParameter(replaceRegxNew, "ResponseDepend", casedataDTO);
            replaceRegxNew = replaceParameter(replaceRegxNew, "PayloadDepend", casedataDTO);
            replaceRegxNew = replaceParameter(replaceRegxNew, "PayloadDependMulti", casedataDTO);
            replaceRegxNew = replaceParameter(replaceRegxNew, "ResponseDependMulti", casedataDTO);
            replaceRegxNew = replaceParameter(replaceRegxNew, "PayloadCurrent", casedataDTO);
            replaceRegxNew = replaceParameter(replaceRegxNew, "ResponseCurrent", casedataDTO);
            replaceRegxNew = replaceSqlQueryDepend(replaceRegxNew, "SqlQueryDepend", casedataDTO);
            replaceRegxNew = replacePayloadDependCurrent(replaceRegxNew, "PayloadDependCurrent", bizcontent,
                    casedataDTO);
            replaceRegxNew = replaceSubString(replaceRegxNew, "RsubStr", bizcontent);
            replaceRegxNew = repalceIdb(replaceRegxNew, "RIdb", casedataDTO);

            /*
             * 此处需要替换表达式，由于String.replaceFrist在替换表达式时碰到问题，所以替换成自定义算法 具体实现为先将json
             * 串中相同的表达式全部替换成MARK,然后再获取表达式的值进行替换
             */
            while (bizcontent.indexOf(MARK) != -1) {
                String keywordExp = replaceRegxNew;
                try {
                    String result="";
					try {
						result = (String) engine.execute(keywordExp);
					} catch (Exception e) {
						// TODO Auto-generated catch block
                        logger.error(String.format("表达式: %s 解析获取到的值为空值,放弃执行后续步骤,请检查", keywordExp));
					}
                    if (!result.equals("")) {
                        bizcontent = bizcontent.replace(MARK, result);
                    } else {
                        //bizcontent = bizcontent.replaceFirst(MARK, result);
                        if (replaceRegxNew.contains("Rreplace")) {
                            bizcontent = bizcontent.replaceFirst(MARK, "");
                        } else {
                            bizcontent = bizcontent.replaceFirst(MARK, replaceRegx);
                        }
                        /*
                         * throw new Exception(
                         * String.format("表达式: %s 解析获取到的值为空值,放弃执行后续步骤,请检查"
                         * ,keywordExp));
                         */
                    }

                } catch (com.googlecode.aviator.exception.CompileExpressionErrorException e) {

                    throw new Exception(String.format("非法表达式:[%s],未定义，解析数据失败，忽略后续测试", keywordExp));
                } catch (com.googlecode.aviator.exception.ExpressionRuntimeException e) {
                    throw new Exception(String.format("非法表达式:[%s],未定义，解析数据失败，忽略后续测试", keywordExp));
                } catch (com.googlecode.aviator.exception.ExpressionSyntaxErrorException e) {
                    throw new Exception(String.format("非法表达式:[%s],未定义，解析数据失败，忽略后续测试", keywordExp));
                }
            }
        }
        return bizcontent;
    }

    public static String replaceParameter(String str1, String str2, CaseDataDTO casedataDTO) {
        if (str1.indexOf(str2 + '(') > -1) {
            str1.replace(" ", "");
            if (str2.equals("ResponseCurrent") || str2.equals("PayloadCurrent")) {
                String index = str2 + "(";
                str1 = str1.replace(index, index + "\"" + casedataDTO.getProjectId() + "_" + casedataDTO.getStep_num()
                        + "\",");
            } else if (str2.equals("ParaReplace")) {
                String analyzedata = casedataDTO.getAnalyze_data();
                int num1 = str1.indexOf(str2 + "(") + str2.length() + 1;
                int num2 = str1.lastIndexOf(",");
                String original_name = str1.substring(num1 + 1, num2 - 1);
                String replace_name = str1.substring(num2 + 1, str1.length() - 1).replace("\"", "");
                String value = "";
                Map<String, String> mapanalyzedata = new HashMap<String, String>();
                mapanalyzedata = Utilities.serializeFormat(analyzedata);
                for (String key : mapanalyzedata.keySet()) {
                    if (key.equals(replace_name) & mapanalyzedata.get(key).trim().length() > 0) {
                        value = mapanalyzedata.get(key);
                        break;
                    } else {
                        value = original_name;
                    }
                }
                str1 = value;
            } else {
                String index = str2 + "(\"";
                str1 = str1.replace(index, index + casedataDTO.getProjectId() + "_");
            }
        }
        return str1;
    }

    public static String replaceSubString(String str1, String str2, String bizcontent) {
        if (str1.indexOf(str2 + "(") > -1) {
            String index = str2 + "(";
            String value = "";
            String bizcontentnew = bizcontent;
            String keyName = str1.substring(index.length(), str1.indexOf(","));
            keyName = keyName.replace("\"", "").replace("\\s", "");
            //处理开放平台=分隔的入参
            String Response = bizcontent.trim().replace("\\", "").replace("\"", "").replace("|", "");
            String firstChar = Response.substring(0, 1);
            if (!firstChar.equals("<") && !firstChar.equals("{") && !firstChar.equals("[")
                    && bizcontent.indexOf("=") > 0) {
                ClientUtils ClientUtils = new ClientUtils();
                JSONObject jsonBzcontent = new JSONObject();
                jsonBzcontent = ClientUtils.Str2JsonObject(bizcontent);
                bizcontentnew = jsonBzcontent.toString();
            }
            try {
                value = ResponseVerify.getTestValue(keyName, bizcontentnew);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                value = keyName;
            }
            //bizcontent = bizcontent.replaceFirst(MARK, value);
            str1 = str1.replace(keyName, value);
        }
        return str1;
    }

    public static String repalceIdb(String str1, String str2, CaseDataDTO casedataDTO) {
        if (str1.indexOf(str2 + '(') > -1) {
            StringBuffer stringBuffer = new StringBuffer(str1);
            if (EnvType.iTest.getCode().equals(casedataDTO.getEnv())) {
                str1 = stringBuffer.insert(5, "\"" + "tst" + "\",").toString();
            } else if (EnvType.uat.getCode().equals(casedataDTO.getEnv())) {
                str1 = stringBuffer.insert(5, "\"" + "pre" + "\",").toString();
            }
        }
        return str1;
    }

    public static String replaceSqlQueryDepend(String str1, String str2, CaseDataDTO casedataDTO) {
        if (str1.indexOf(str2 + '(') > -1) {
            str1.replace(" ", "");
            String index = str2 + "(\"";
            int beginIndex = str1.indexOf(index) + index.length();
            StringBuffer stringBuffer = new StringBuffer(str1);
            str1 = stringBuffer.insert(beginIndex, casedataDTO.getProjectId() + "_" + casedataDTO.getStep_num() + "_")
                    .toString();
        }
        return str1;
    }

    public static String replacePayloadDependCurrent(String replaceRegxNew, String methodName, String bizcontent,
                                                     CaseDataDTO casedataDTO) {
        if (replaceRegxNew.indexOf(methodName + '(') > -1) {
            String listPath = casedataDTO.getProjectId();
            String caseNo = casedataDTO.getCaseNo();
            String regx = methodName + "\\([\\s]*\"(.*)\"[\\s]*\\)";
            Pattern p = Pattern.compile(regx);
            Matcher m = p.matcher(replaceRegxNew);
            while (m.find()) {
                String replaceValue = listPath + "_" + caseNo + "_" + methodName;
                replaceRegxNew = replaceRegxNew.replace(m.group(1), replaceValue + "_" + m.group(1));
                //存入数据到payLoad环境变量
                ExpressionRegister.setPayloadEnv(replaceValue, bizcontent);
            }
        }
        return replaceRegxNew;
    }

    //map连接查询key值
    public static String getJoinMapData(String knownMapKey, String knownMapValue, Map<String, String[]> getMapData,
                                        String getKey) {
        String getValue = "";
        String knownArry[] = getMapData.get(knownMapKey);
        int num = 0;
        for (int i = 0; i < knownArry.length; i++) {
            String value = knownArry[i];
            if (knownMapValue.equals(value)) {
                num = i;
            }
        }

        String getKeyArry[] = getMapData.get(getKey);
        getValue = getKeyArry[num];
        return getValue;
    }

}
