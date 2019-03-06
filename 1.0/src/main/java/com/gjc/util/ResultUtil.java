package com.gjc.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回值工具包
 * 
 * @author 顾继钞
 * @date 2018年8月16日 上午9:44:25
 */
public class ResultUtil {
	/**
	 * 列表返回值
	 * 
	 * @author 顾继钞
	 * @date 2018年8月16日 上午9:45:03
	 * @param partList
	 *            分页后的列表
	 * @param pageIndex
	 *            当前页面
	 * @param pageSize
	 *            页面大小
	 * @param total
	 *            总条数
	 * @return
	 */
	public static Map<String, Object> listResult(Integer pageIndex, Integer pageSize, Integer total,Object partList) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Integer pages = total / pageSize + 1;
		if (total % pageSize == 0) {
			pages = total / pageSize;
		}
		map.put("pageIndex", pageIndex);
		map.put("pageSize", pageSize);
		map.put("pages", pages);
		map.put("total", total);
		map.put("partList", partList);
		dataMap.put("code", 1);
		dataMap.put("data", map);
		dataMap.put("message", "获取成功");
		return dataMap;
	}

	/**
	 * 成功返回
	 * 
	 * @author 顾继钞
	 * @date 2018年8月16日 上午9:48:20
	 * @param status
	 *            状态码
	 * @param remind
	 *            提醒信息
	 * @return
	 */
	public static Map<String, Object> resultSuccess(String remind, Integer status,Object data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 1);
		map.put("message", "success");
		map.put("data", data);
		if (null != remind) {
			map.put("message", remind);
		}
		return map;
	}

	/**
	 * 失败返回
	 * 
	 * @author 顾继钞
	 * @date 2018年8月16日 上午9:48:53
	 * @param status
	 *            状态码
	 * @param remind
	 *            提醒信息
	 * @return
	 */
	public static Map<String, Object> resultFail(String remind, Integer status,Object data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 3);
		map.put("message", "fail");
		map.put("data", data);
		if (null != remind) {
			map.put("message", remind);
		}
		return map;
	}

}
