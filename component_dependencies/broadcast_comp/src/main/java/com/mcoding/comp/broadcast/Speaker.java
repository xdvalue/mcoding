package com.mcoding.comp.broadcast;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * 播音控件，
 * @author hzy
 *
 */
public class Speaker {
	
	public static void speak(String msg, Integer volume, Integer rate){
		//初始化语音播放控件
		ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
		// 音量 0-100
		volume = volume==null ? 100: volume;
        sap.setProperty("Volume", new Variant(volume));
        // 语音朗读速度 -10 到 +10
        rate = rate == null ? -2 :rate;
        sap.setProperty("Rate", new Variant(rate));
        
		Dispatch sapo = sap.getObject();
		Dispatch.call(sapo, "Speak", new Variant(msg));
	}
	
	public static void main(String[] args) {
		speak("来一起说，1 2 3 4 5 6 7 8", null, null);
//		System.out.println(System.getProperties());
		System.out.println(System.getProperty("sun.cpu.isalist"));
		System.out.println(System.getProperty("os.arch"));
		System.out.println(System.getProperty("sun.arch.data.model"));
		
	}

}
