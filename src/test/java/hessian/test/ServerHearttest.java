package hessian.test; 

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.za.qa.hessianservice.HeartService;


/** 
 * @author jujinxin 
 * @version 创建时间：2017年2月14日 下午4:30:34 
 * 类说明 
 */
public class ServerHearttest {

	@Test
	public void serverhearttest() {
		HessianProxyFactory factory = new HessianProxyFactory();
		// factory.setServiceUrl("http://localhost:8080/HelloService");
		// factory.setServiceInterface(HelloService.class);
		try {

			HeartService heartService = (HeartService) factory.create(
					HeartService.class,
					"http://10.139.162.247:9090/heartService");
			List<Object> list =heartService.heart();
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}