package com.mcoding.comp.broadcast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	//在eclipse上面运行，需要配置 buildPath --》Source --》NativeLiberarlocation --》 edit --》broadcast_comp/src/main/resources
	public static void main(String[] args) throws InterruptedException, IOException {
		
		ParkingMsg msg1 = new ParkingMsg("粤B1111", "1", "10", "东码头");
		ParkingMsg msg2 = new ParkingMsg("粤B2222", "2", "10", "东码头");
		ParkingMsg msg3 = new ParkingMsg("粤B3333", "3", "10", "东码头");
		ParkingMsg msg4 = new ParkingMsg("粤B4444", "4", "10", "东码头");
		
		final List<ParkingMsg> msgList = new ArrayList<>();
		msgList.add(msg1);
		msgList.add(msg2);
		msgList.add(msg3);
		msgList.add(msg4);
		
		
		BroadcastManager.getInstance().start();
		for(int i=0; i<msgList.size(); i++){
			final int index = i;
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						BroadcastManager.getInstance().broadcast(msgList.get(index));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			thread.start();
		}
		
		Thread.sleep(60 * 1000);
		BroadcastManager.getInstance().stop();
		
	}
	
	/*public static void speakSth(String sth){
		// ？？ 这个Sapi.SpVoice是需要安装什么东西吗，感觉平白无故就来了
	    ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
	    // Dispatch是做什么的？
	    Dispatch sapo = sap.getObject();
	    try {
	 
	        // 音量 0-100
	        sap.setProperty("Volume", new Variant(100));
	        // 语音朗读速度 -10 到 +10
	        sap.setProperty("Rate", new Variant(-2));
	 
	        Variant defalutVoice = sap.getProperty("Voice");
	 
	        Dispatch dispdefaultVoice = defalutVoice.toDispatch();
	        Variant allVoices = Dispatch.call(sapo, "GetVoices");
	        Dispatch dispVoices = allVoices.toDispatch();
	 
	        Dispatch setvoice = Dispatch.call(dispVoices, "Item", new Variant(1)).toDispatch();
	        ActiveXComponent voiceActivex = new ActiveXComponent(dispdefaultVoice);
	        ActiveXComponent setvoiceActivex = new ActiveXComponent(setvoice);
	 
	        Variant item = Dispatch.call(setvoiceActivex, "GetDescription");
	        // 执行朗读
//	        Dispatch.call(sapo, "Speak", new Variant("曾发明小朋友，早上好"));
//	        Dispatch.call(sapo, "Speak", new Variant("好你麻痹111111111111111"));
	        Dispatch.call(sapo, "Speak", new Variant(sth));
	       
	 
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        sapo.safeRelease();
	        sap.safeRelease();
	    }
	}*/

}
