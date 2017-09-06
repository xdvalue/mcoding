package com.mcoding.comp.broadcast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DllLoadUtils {
	
	
	public static void loadDllLib(String dllPath) throws IOException {
		InputStream dll64 = BroadcastManager.class.getResourceAsStream("/" + dllPath);
		
		System.out.println("dll:" + dll64);
		
		String filePath  = new File("").getAbsolutePath() + File.separator + dllPath; 
		File dll = new File(filePath); 
		if (!dll.exists()) {
			createFile(dll, dll64);
		}
        System.load(dll.getAbsolutePath());
        dll.deleteOnExit();  
	}
	
	private static void createFile(File dllInside, InputStream dllOutside) throws IOException{
        FileOutputStream out = new FileOutputStream(dllInside);
        
        int i;  
        byte[] buf = new byte[1024];  
        try {  
            while ((i = dllOutside.read(buf)) != -1) {  
                out.write(buf, 0, i);  
            }  
        } finally {  
        	dllOutside.close();  
            out.close();  
        }  
	}

}
