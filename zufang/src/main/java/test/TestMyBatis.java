package test;  
  
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;  
  
import org.apache.log4j.Logger;  
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
  
import com.alibaba.fastjson.JSON;  
import com.entity.domain.House;
import com.entity.domain.Orders;
import com.entity.domain.User;
import com.entity.service.IHouseService;
import com.entity.service.IOrdersService;
import com.entity.service.IUserService;
  
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
  
public class TestMyBatis {  
    private static Logger logger = Logger.getLogger(TestMyBatis.class);  
//  private ApplicationContext ac = null;  
    @Resource  
    private IUserService userService;  
    @Resource  
    private IHouseService houseService; 
    @Resource  
    private IOrdersService ordersService;  
    @Test  
    public void test1() {  
    	try {
    		/*for(int i =0;i<20;i++)
    		{
    			House house = new House();
    			house.setHouseaddress("廣州市天河區黃埔大道"+getRandomNum(1, 500)+"號");
    			house.setHousearea(""+getRandomNum(50, 150));
    			house.setHownerid(2);
    			house.setOrientation(getRandomOrientation());
    			house.setPnumber("15521380978");
    			house.setPrice(getRandomNum(500,1500));
    			house.setReleasetime(new Date());
    			house.setRoomnum(getRandomNum(2, 5));
    			houseService.save(house);
    			
    		}*/
    		List<Orders> orders = ordersService.getOrdersByUId((long)5);
    		System.out.println(JSON.toJSONString(orders).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        // System.out.println(user.getUserName());  
        // logger.info("值："+user.getUserName());  
    } 
    public static int getRandomNum(int min,int max) {
    	int num = (int)(min+Math.random()*(max-1+1));
    	return num;
	}
    public static String getRandomOrientation()
    {
    	String string = "";
    	switch (getRandomNum(1, 4)) {
		case 1:
			string = "東";
			break;
		case 2:
			string = "南";
			break;
		case 3:
			string = "西";
			break;
		case 4:
			string = "北";
			break;
		default:
			break;
		}
    	return string;
    }
	public String GetNowDate(){   
	    String temp_str="";   
	    Date dt = new Date();   
	    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	    temp_str=sdf.format(dt);   
	    return temp_str;   
	} 
}  
