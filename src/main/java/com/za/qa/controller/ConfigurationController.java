package com.za.qa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.za.qa.domain.dto.Configuration;
import com.za.qa.mapper.ConfigurationMapper;

/**
 * @author jujinxin
 * @version 创建时间：2017年1月17日 上午10:11:16 类说明
 */
@RestController
@RequestMapping("/configuration")
public class ConfigurationController {
    @Autowired
    ConfigurationMapper configurationMapper;

    //url template中带参数
    @RequestMapping(value = "/{iTest}", method = RequestMethod.GET)
    @ResponseBody
    public List<Configuration> findconfiguration(@PathVariable("iTest") String env) {
        PageHelper.startPage(1, 1);
        List<Configuration> list = (List<Configuration>) configurationMapper.findConfigurationBy(env);

        return list;
    }

    //form参数
    @RequestMapping(value = "/iTest2", method = RequestMethod.POST)
    public @ResponseBody List<Configuration> findconfiguration2(@RequestParam(value = "env", required = true) String env) {
        List<Configuration> list = (List<Configuration>) configurationMapper.findConfigurationBy(env);
        return list;
    }

    //json参数
    @RequestMapping(value = "/iTest3", method = RequestMethod.POST)
    public @ResponseBody List<Configuration> findconfiguration3(@RequestBody Configuration configuration) {
        List<Configuration> list = (List<Configuration>) configurationMapper
                .findConfigurationBy(configuration.getEnv());
        return list;
    }

    @RequestMapping(value = "/login/{name}&{pwd}", method = RequestMethod.GET)
    public String login(@PathVariable String name, @PathVariable String pwd) {
        if (name.equals("admin") && pwd.equals("admin")) {
            return "hello welcome admin";
        } else {
            return "oh sorry user name or password is wrong";
        }
    }

    /**
     * 通过get请求去登陆
     * 
     * @param name
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/loginbyget", method = RequestMethod.GET,produces = "text/x-www-form-urlencoded;charset=UTF-8")
    public String loginByGet(@RequestParam(value = "name", required = true) String name,
                             @RequestParam(value = "pwd", required = true) String pwd) {
        return login4Return(name, pwd);
    }

    /**
     * 通过post请求去登陆
     * 
     * @param name
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/loginbypost", method = RequestMethod.POST)
    public String loginByPost(@RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "pwd", required = true) String pwd) {
        System.out.println("hello post");
        return login4Return(name, pwd);
    }

    /**
     * 对登录做出响应处理的方法
     * 
     * @param name 用户名
     * @param pwd 密码
     * @return 返回处理结果
     */
    private String login4Return(String name, String pwd) {
        String result;
        if (name.equals("admin") && pwd.equals("admin")) {
            result = "hello welcome admin";
        } else {
            result = "oh sorry user name or password is wrong";
        }
        System.out.println("收到请求,请求结果:" + result);
        return result;
    }
}
