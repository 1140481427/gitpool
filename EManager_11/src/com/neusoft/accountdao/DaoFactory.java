package com.neusoft.accountdao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {

	private DaoFactory() {
	}
	
	private static Properties ps = new Properties();
	
	static {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("dao.properties");
		try {
			ps.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static <T> T  getInstance(String daoName,Class<T> clss){
		T t = null;
		
		if(daoName == null || daoName == ""){
			return t;
		}
		String className = ps.getProperty(daoName);
		if(className == null || className == ""){
			return t;
		}
		try {
			Class<?> clz = Class.forName(className);
			try {
			t = (T) clz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return t;
	}

}
