package com.mcoding.base.ui.service.store;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.bean.store.StoreExample;

public interface StoreService extends BaseService<Store, StoreExample> {
	
//	public Store queryStoreByRequest(String schemem, String serverName, int serverPort, String requestUri);

	public Store queryStoreByRequest(String schemem, String serverName, String requestUri);
}
