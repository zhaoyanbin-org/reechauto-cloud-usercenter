package com.reechauto.cloud.provider;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicProviderApplicationTests {
	
	
	
	
	
	private ExecutorService executorService  = new ThreadPoolExecutor(10, 10000, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1000));
	
    @Autowired
	private RedisTemplate<String , Object>  redisTemplate;
	@Test
	public void contextLoads() {
		
		for (int i = 0; i < 100; i++) {
			
	
			final int j = i;
			executorService.execute(new Runnable() {

				@Override
				public void run() {

					try {
						
						redisTemplate.delete("key"+j);
						
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});
			
		}
		
		
		
		
		}		

}
