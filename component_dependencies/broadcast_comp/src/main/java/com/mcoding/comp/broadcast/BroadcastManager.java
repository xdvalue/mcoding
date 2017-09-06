package com.mcoding.comp.broadcast;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BroadcastManager {
	
	private static Logger logger = LoggerFactory.getLogger(BroadcastManager.class);
	
	private static BroadcastManager broadcastManager = new BroadcastManager();
	private BlockingQueue<IBroadcastMsg> queue = new LinkedBlockingQueue<>();
	private boolean isStart = false;
	private boolean isSpeaking = false;
	private static Thread mainThread;
	
	public static BroadcastManager getInstance(){
		return broadcastManager;
	}
	
	public void broadcast(IBroadcastMsg msg) throws InterruptedException{
		broadcastManager.getQueue().put(msg);
	}

	private BlockingQueue<IBroadcastMsg> getQueue() {
		return queue;
	}
	
	private BroadcastManager(){
		super();
	}
	
	protected void speakFromQueue() throws InterruptedException{
		while(this.isStart){
			logger.info("waiting next............");
			IBroadcastMsg msg = this.queue.take();
			
			String content = msg.getContent();
			logger.info("begin speaking:" + content);
			if (content==null || content.trim().equals("")) {
				continue;
			}
			long l1 = System.currentTimeMillis();
			this.isSpeaking = true;
			Speaker.speak(content, msg.getVolume(), msg.getRate());
			this.isSpeaking = false;
			
			logger.info("end speaking, cost "+ (System.currentTimeMillis() -l1) +"ms :" + content);
		}
	}
	
	public boolean isStarted(){
		return this.isStart;
	}
	
	public void start() throws InterruptedException, IOException{
		String archModel = System.getProperty("sun.arch.data.model");
		logger.info("archModel:" + archModel);
		if(archModel.contains("64")){
			DllLoadUtils.loadDllLib("jacob-1.17-x64.dll");
		}else{
			DllLoadUtils.loadDllLib("jacob-1.17-x86.dll");
		}
		
		logger.info("starting broadcast.........");
		if (isStart) {
			return;
		}
		if(mainThread != null && !mainThread.isInterrupted()){
			mainThread.interrupt();
		}
		
		this.isStart = true;
		mainThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					speakFromQueue();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		mainThread.start();
		logger.info("started broadcast.........");
	}
	
	public void stop(){
		logger.info("stoping broadcast.................");
		if(mainThread != null && !mainThread.isInterrupted()){
			this.isStart = false;
			mainThread.interrupt();
		}
		
		this.isStart = false;
		logger.info("stoped broadcast.................");
	}

	public boolean isSpeaking() {
		return isSpeaking;
	}
	
}
