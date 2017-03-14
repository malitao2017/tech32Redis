package redis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class RedisTXDemo {
	public static Jedis getJedis(){
		Jedis jedis = null;
		jedis = new Jedis("172.16.14.92",6379);
//		jedis.auth("password");
		return jedis;
	}
	//没有异常的事务  
	public void txDemo(){
		Jedis jedis = getJedis();
		//开启事务  
		Transaction tx = jedis.multi();
		tx.set("txcity", "beijing");
		tx.sadd("hi", "abc");
		tx.get("txcity");
		List<Object> os = tx.exec();
		for(Object o:os){
			System.out.println(o.toString());
		}
		System.out.println("---------------------");
	}
	//含有异常的事务  
	public void txHasExDemo(){
		Jedis jedis = getJedis();
		 //开启事务  
		Transaction tx = jedis.multi();
		tx.set("txcity1", "beijing");
		tx.sadd("hi1", "abc");
		try{
			tx.get("txcity1");
			int i = 1/0;
		}catch(Exception e){
			List<Object> os = tx.exec();
			for(Object o:os){
				System.out.println(o.toString());
			}
		}
		
	}
	public static void main(String[] args) {
		RedisTXDemo demo = new RedisTXDemo();
		demo.txDemo();
		demo.txHasExDemo();
	}
}
