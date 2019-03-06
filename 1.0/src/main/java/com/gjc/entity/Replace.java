package com.gjc.entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Replace {
	private static String controllerPackage;
	private static String entityPackage;
	private static String daoPackage;
	private static String servicePackage;
	private static String serviceImplPackage;
	private static String author;
	private static String date;
	private static String serviceSelf;
	private static String controllerSelf;
	
	private static Map<String,String> map;
	static {
		InputStreamReader reader;
		try {
			reader = new InputStreamReader(new FileInputStream("src/main/resources/replace.properties"),"utf-8");
			Properties props = new Properties();  
			try {
				Map<String, String> mapTemp = new HashMap<String, String>();
				props.load(reader);
				controllerPackage = props.getProperty("controllerPackage");
				entityPackage = props.getProperty("entityPackage");
				daoPackage = props.getProperty("daoPackage");
				servicePackage = props.getProperty("servicePackage");
				serviceImplPackage = props.getProperty("serviceImplPackage");
				author = props.getProperty("author");
				date = props.getProperty("date");
				serviceSelf = props.getProperty("serviceserviceSelf");
				controllerSelf =  props.getProperty("controllerSelf");
				
				mapTemp.put("controllerPackage",controllerPackage);
				mapTemp.put("entityPackage",props.getProperty("entityPackage"));
				mapTemp.put("daoPackage",props.getProperty("daoPackage"));
				mapTemp.put("servicePackage",props.getProperty("servicePackage"));
				mapTemp.put("serviceImplPackage",props.getProperty("serviceImplPackage"));
				mapTemp.put("author",props.getProperty("author"));
				mapTemp.put("date",props.getProperty("date"));
				mapTemp.put("serviceSelf",props.getProperty("serviceSelf"));
				mapTemp.put("controllerSelf",props.getProperty("controllerSelf"));
				map = mapTemp;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
	}
	
	public static String getServiceSelf() {
		return serviceSelf;
	}
	public static void setServiceSelf(String serviceSelf) {
		Replace.serviceSelf = serviceSelf;
	}
	public static String getControllerSelf() {
		return controllerSelf;
	}
	public static void setControllerSelf(String controllerSelf) {
		Replace.controllerSelf = controllerSelf;
	}
	public static String getControllerPackage() {
		return controllerPackage;
	}
	public static void setControllerPackage(String controllerPackage) {
		Replace.controllerPackage = controllerPackage;
	}
	public static String getEntityPackage() {
		return entityPackage;
	}
	public static void setEntityPackage(String entityPackage) {
		Replace.entityPackage = entityPackage;
	}
	public static String getDaoPackage() {
		return daoPackage;
	}
	public static void setDaoPackage(String daoPackage) {
		Replace.daoPackage = daoPackage;
	}
	public static String getServicePackage() {
		return servicePackage;
	}
	public static void setServicePackage(String servicePackage) {
		Replace.servicePackage = servicePackage;
	}
	public static String getAuthor() {
		return author;
	}
	public static void setAuthor(String author) {
		Replace.author = author;
	}
	public static String getDate() {
		return date;
	}
	public static void setDate(String date) {
		Replace.date = date;
	}
	public static String getServiceImplPackage() {
		return serviceImplPackage;
	}
	public static void setServiceImplPackage(String serviceImplPackage) {
		Replace.serviceImplPackage = serviceImplPackage;
	}
	public static Map<String, String> getMap() {
		return map;
	}
	public static void setMap(Map<String, String> map) {		
		Replace.map = map;
	}
	public static String getserviceSelf() {
		return serviceSelf;
	}
	public static void setserviceSelf(String serviceSelf) {
		Replace.serviceSelf = serviceSelf;
	}
	
	
}
