package com.mcoding.base.ui.utils;

import com.mcoding.base.ui.bean.store.Store;

public class StoreUtils {
	
public static final ThreadLocal<Store> threadLocal = new ThreadLocal<>();
	
	public static void setInThreadLocal(Store store){
		threadLocal.set(store);
	}
	
	public static Store getStoreFromThreadLocal(){
		return threadLocal.get();
	}
	
	public static Integer getStoreIdFromThreadLocal(){
		Store store = getStoreFromThreadLocal();
		if (store == null) {
			return null;
		}
		return store.getId();
	}

}
