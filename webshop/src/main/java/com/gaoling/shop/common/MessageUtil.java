package com.gaoling.shop.common;

import java.util.HashMap;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.gaoling.shop.task.NotifyTask;

import net.sf.json.JSONObject;

public class MessageUtil {

	private ThreadPoolTaskExecutor pool;
	private static MessageUtil message;
	
	//初始化线程池
	private MessageUtil(){
		pool=new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(Integer.parseInt(PropertiesUtil.getProperty("threadPool.corePoolSize")));
		pool.setMaxPoolSize(Integer.parseInt(PropertiesUtil.getProperty("threadPool.maxPoolSize")));
		pool.setQueueCapacity(Integer.parseInt(PropertiesUtil.getProperty("threadPool.queueCapacity")));
		pool.setKeepAliveSeconds(Integer.parseInt(PropertiesUtil.getProperty("threadPool.keepAliveSeconds")));
		pool.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				NotifyTask task=(NotifyTask)r;
				String[] currentDate=DateUtil.getCurrentTime().split(" ");
				Logger.getLogger("msg").info(currentDate[0]+"|"+currentDate[1]+"|"+task.getAccessToken()
						+"|"+JSONObject.fromObject(task.getParams()).toString()
						+"|the current number of threads in the pool:"+executor.getPoolSize()
						+",number of threads that are actively executing tasks:"+executor.getActiveCount()
						+",total number of tasks that have ever been scheduled for execution:"+executor.getTaskCount());
			}
		});
		pool.initialize();
	}
	
	//向指定微信用户发送通知
	public void sendMessage(String accessToken,HashMap<String,Object> params){
		NotifyTask task=new NotifyTask(accessToken,params);
		pool.execute(task);
	}

	public synchronized static MessageUtil getInstance() {
		if(null==message){
			message=new MessageUtil();
		}
		return message;
	}
	
}
