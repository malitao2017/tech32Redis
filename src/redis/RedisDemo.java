package redis;

import redis.clients.jedis.Jedis;

public class RedisDemo {
	/**
	 * 本次测试使用的是本地的虚拟机中的ubantu
	 */
	static String IP = "172.16.14.92"; //redis服务器的ip和端口  
	static String PASSWORD = "";//连接redis的密码  
	/**
	 * 本机使用有线vm中的linux可以上网
	 * 如果出现Java无法连接redis的异常，主要有三个方面的问题：
1.Java运行的主机ping一下redis服务器所在主机，看能否ping通
2.关闭redis服务器所在主机的防火墙
3.打开redis安装目录下的redis.conf文件。注释掉：bind 127.0.0.1这句话
	 * @return
	 */
	public static Jedis getJedis(){
		Jedis jedis = null;
		jedis = new Jedis(IP,6379);
//		jedis.auth(PASSWORD);//本机安装的没有设置密码
		return jedis;
	}
	
	public void test(){
		Jedis jedis = null;
		try{
			jedis = getJedis();
			long dbsize = jedis.dbSize();
			System.out.println("数据库大小："+dbsize);
			System.out.println("测试test的值："+jedis.get("test"));
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(null != jedis){
				jedis.disconnect();
			}
		}
	}
	
	public static void main(String[] args) {
		RedisDemo demo = new RedisDemo();
		demo.test();
	}
	
}
