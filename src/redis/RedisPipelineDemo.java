package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class RedisPipelineDemo {
	public static Jedis getJedis(){
		Jedis jedis = null;
		jedis = new Jedis("172.16.14.92",6379);
//		jedis.auth("password");
		return jedis;
	}
	
	public void hasPipeline(){
		Jedis jedis = getJedis();
		long begin = System.currentTimeMillis();
		Pipeline pl = jedis.pipelined();
		for(int i=0;i<1000;i++){
			pl.incr("testPL");
		}
		pl.sync();
		System.out.println("管道耗时："+(System.currentTimeMillis()-begin));
	}
	public void noPipeline(){
		Jedis jedis = getJedis();
		long begin = System.currentTimeMillis();
		for(int i=0;i<1000;i++){
			jedis.incr("testPL");
		}
		System.out.println("非管道耗时："+(System.currentTimeMillis()-begin));
	}
	/**
	 * 管道的作用就是让命令能批量执行，从而节省时间
	 * @param args
	 */
	public static void main(String[] args) {
		RedisPipelineDemo demo = new RedisPipelineDemo();
		demo.hasPipeline();
		demo.noPipeline();
	}
}
