package com.za.qa.hessianservice; 

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年2月14日 下午4:15:57 
 * 类说明 
 */

@Service("heartService")
public class HeartServiceImpl implements HeartService {
	
	@Value("${server.port}")
	private int port;
	
			
	@Override
	//@Scheduled(fixedRate = 3000)
    public List<Object> heart() throws UnknownHostException, IOException {
		Socket sender = new Socket(InetAddress.getLocalHost(), port);
		List<Object> list = new LinkedList<Object>();

        try {          	
            	list.add(sender.getLocalAddress());
            	list.add(sender.getPort());
            	list.add("欢迎使用众安接口自动化测试平台");
            	list.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            	//System.out.println("服务运行中。。。。");
             /*   synchronized (ServerHeart.class) {
                    // this.wait(5000);
                    Thread.sleep(5000);
                }*/
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
	
	

}
