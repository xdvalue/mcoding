package com.mcoding.comp.sms;

import com.mcoding.comp.sms.chuanglan.ChuanglanAccount;
import com.mcoding.comp.sms.tcbj.TcbjAccount;
import com.mcoding.comp.sms.utils.Supplier;

public class SupplierAccountFactory {
	
	private SupplierAccountFactory(){
		super();
	}

	public static SupplierAccount build(Supplier supplier) {
		SupplierAccount account = null;
		
		switch (supplier) {
		case BAY_YUE:
			account = new TcbjAccount("vipgzxd_xd", "U2Ty7d12");
			break;

		case XI_DING:
			account = new ChuanglanAccount("vipgzxd_xd", "123456Qw");
			break;
			
		case BAI_XI:
			account = new ChuanglanAccount("vipgzxd_bx", "EFsafew!");
			break;
			
		case DT_VALUE:
			account = new ChuanglanAccount("vipgzxd_dt_banner", "Rd5wg@FF");
			break;
			
		case MAI_CHE:
			account = new ChuanglanAccount("vipgzxd_maiche", "maiche123Ab");
			break;
		}

		return account;
	}

}
