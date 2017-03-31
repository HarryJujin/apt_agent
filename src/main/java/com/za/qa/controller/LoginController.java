package com.za.qa.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.za.qa.domain.dto.User;

/**
 * @author jujinxin
 * @version 创建时间：2017年1月17日 下午4:12:25 类说明
 */
@RestController
public class LoginController {
    /**
     * index home
     * 
     * @return
     */
    @RequestMapping(value = "/home")
    public String home() {
        return "index home";
    }

    /**
     * 得到1个参数
     * 
     * @param name 用户名
     * @return 返回结果
     */
    /*
     * @GetMapping(value = "/{name}") public String index(@PathVariable String
     * name) { return "oh you are " + name + "<br> nice to meet you";//
     * \n不起作用了,那就直接用html中的标签吧 }
     */

    /**
     * 简单post请求
     * 
     * @param name
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/testpost", method = RequestMethod.POST)
    public String testpost() {
        System.out.println("hello  test post");
        return "ok";
    }

    /**
     * 同时得到两个参数
     * 
     * @param name 用户名
     * @param pwd 密码
     * @return 返回结果
     */
    @RequestMapping(value = "/loginurlget", method = RequestMethod.GET)
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
     * @return required=false表示不传的话，会给参数赋值为null，required=true就是必须要有
     */
    @RequestMapping(value = "/loginbyget", method = RequestMethod.GET)
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
    @RequestMapping(value = "/loginbypost", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String loginByPost(@RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "pwd", required = true) String pwd) {
        System.out.println("hello post");
        return login4Return(name, pwd);
    }

    /**
     * 参数为一个bean对象.spring会自动为我们关联映射
     * 
     * @param loginBean
     * @return
     */
    @RequestMapping(value = "/loginbypost1", method = { RequestMethod.POST, RequestMethod.GET })
    public String loginByPost1(User user) {
        if (null != user) {
            return login4Return(user.getName(), user.getPwd());
        } else {
            return "error";
        }
    }

    /**
     * 请求内容是一个json串,spring会自动把他和我们的参数bean对应起来,不过要加@RequestBody注解
     * 
     * @param name
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/loginbypost2", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
    public String loginByPost2(@RequestBody User user) {
        if (null != user) {
            return login4Return(user.getName(), user.getPwd());
        } else {
            return "error";
        }
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
