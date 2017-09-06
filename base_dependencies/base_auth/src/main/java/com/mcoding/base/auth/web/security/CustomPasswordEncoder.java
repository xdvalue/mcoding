package com.mcoding.base.auth.web.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mcoding.base.auth.utils.AuthConstant;
import com.mcoding.base.utils.encryption.DES;

/**
 * Created by LiBing on 2014/6/5.
 * 系统用户密码加密
 */
@Component("customPasswordEncoder")
public class CustomPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		try {
			DES des = new DES(AuthConstant.SECRET_KEY);
			return des.encrypt(rawPassword.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		try {
			DES des = new DES(AuthConstant.SECRET_KEY);
            String encOrig = des.encrypt(rawPassword.toString());
            return encodedPassword.equals(encOrig);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}
}
