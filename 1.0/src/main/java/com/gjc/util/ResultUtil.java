package com.gjc.util;

import net.sf.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回值工具包
 * 1.常规对象返回格式(可自定义:appendDataToData往data下面插入数据,appendDataToOut往外部插入数据)
 * {
 * 	"code":1,
 * 	"message":"success",
 * 	"data":{}
 * }
 *
 * 2.常规列表返回格式(可自定义,外部操作列表使用pagingList)
 * {
 * 	"code":1,
 * 	"message":"success",
 * 	"data"{
 * 		"partList":[]
 * 	}
 * }
 *
 * 3.特殊列表返回格式使用对象返回(即方式1)
 *
 * @author 顾继钞
 * @date 2018年8月16日 上午9:44:25
 */
public class ResultUtil {

	public static final String CODE = "code";
	public static final String DATA = "data";
	public static final String MESSAGE = "message";

	public static final String MESSAGE_DEFAULT_SUCCESS = "success";
	public static final String MESSAGE_DEFAULT_FAIL = "fail";

	/**
	 * 返回值
	 * 
	 * @author 顾继钞
	 * @date 2018年11月26日 下午2:05:49
	 * @param code
	 *            返回状态
	 * @param message
	 *            返回提醒
	 * @param data
	 *            返回数据
	 * @return
	 */
	public static ResultDataBuilder resultObject(Integer code, String message, Object data) {
		ResultDataBuilder resultDataBuilder = new ResultDataBuilder();
		resultDataBuilder.build(code, message, data);
		return resultDataBuilder;
	}

	/**
	 * 返回值
	 * 
	 * @author 顾继钞
	 * @date 2018年11月26日 下午2:22:35
	 * @param code
	 *            返回状态
	 * @param message
	 *            返回提醒
	 * @return
	 */
	public static ResultDataBuilder resultObject(Integer code, String message) {
		ResultDataBuilder resultDataBuilder = new ResultDataBuilder();
		resultDataBuilder.build(code, message);
		return resultDataBuilder;
	}



	/**
	 * 返回值
	 * 
	 * @author 顾继钞
	 * @date 2018年11月26日 下午2:46:02
	 * @param code
	 *            返回状态
	 * @return
	 */
	public static ResultDataBuilder resultObject(Integer code) {
		ResultDataBuilder resultDataBuilder = new ResultDataBuilder();
		resultDataBuilder.build(code);
		return resultDataBuilder;
	}

	/**
	 * 返回分页列表(不分页,只包装返回)
	 *
	 * @author 顾继钞
	 * @date 2018年12月28日 下午3:59:56
	 * @param pageIndex
	 *            当前页
	 * @param pageSize
	 *            页面大小
	 * @param total
	 *            总数
	 * @param partList
	 *            分页好的数据
	 * @return
	 */
	public static ResultDataBuilder resultPartList(Integer pageIndex, Integer pageSize, Integer total,
												   Object partList) {
		Integer pages = 0;
		if (null != pageSize && pageSize.intValue() != 0) {
			pages = total / pageSize + 1;
			if (total % pageSize == 0) {
				pages = total / pageSize;
			}
		}
		ResultDataBuilder resultDataBuilder = new ResultDataBuilder();
		resultDataBuilder.build(1, "success")
				.appendToData("partList", partList).appendToData("pageIndex", pageIndex)
				.appendToData("pageSize", pageSize).appendToData("total", total)
				.appendToData("pages", pages);
		return resultDataBuilder;
	}

	/**
	 * 分页后返回
	 *
	 * @author 顾继钞
	 * @date 2018年12月28日 下午4:01:34
	 * @param pageIndex 当前页
	 * @param pageSize  页面大小
	 * @param allList 所有列表
	 * @return
	 */
	public static <T> ResultDataBuilder resultPagingList(Integer pageIndex, Integer pageSize, List<T> allList) {
		Integer total = allList.size();
		Integer pages = total / pageSize + 1;
		if (total % pageSize == 0) {
			pages = total / pageSize;
		}
		Integer fromIndex = (pageIndex - 1) * pageSize;
		Integer toIndex = fromIndex + pageSize;
		if (toIndex >= total) {
			toIndex = total;
		}
		List<T> partList = new ArrayList<>();
		if (pageIndex <= pages || pages == 0) {
			partList = allList.subList(fromIndex, toIndex);
		}
		ResultDataBuilder resultDataBuilder = new ResultDataBuilder();
		resultDataBuilder.build(1, "success")
				.appendToData("partList", partList).appendToData("pageIndex", pageIndex)
				.appendToData("pageSize", pageSize).appendToData("total", total)
				.appendToData("pages", pages);
		return resultDataBuilder;
	}

	/**
	 * 截取列表
	 * @author 顾继钞
	 * @date 2018年12月28日 下午4:09:32
	 * @param pageIndex
	 * @param pageSize
	 * @param allList
	 * @return
	 */
	public static <T> List<T> pagingList(Integer pageIndex, Integer pageSize, List<T> allList) {
		Integer total = allList.size();
		Integer pages = total / pageSize + 1;
		if (total % pageSize == 0) {
			pages = total / pageSize;
		}
		Integer fromIndex = (pageIndex - 1) * pageSize;
		Integer toIndex = fromIndex + pageSize;
		if (toIndex >= total) {
			toIndex = total;
		}
		List<T> partList = new ArrayList<>();
		if (pageIndex <= pages || pages == 0) {
			partList = allList.subList(fromIndex, toIndex);
		}
		return partList;
	}

	public static class ResultDataBuilder {
		private Map<String, Object> response;

		private Map<String, Object> data;

		private ResultDataBuilder() {
			this.response = new HashMap<>();
		}

		/**
		 * 创建
		 *
		 * @return
		 */
		public ResultDataBuilder build(Integer code, String message, Object data) {
			response.put(CODE, code);
			response.put(MESSAGE, message);
			response.put(DATA, data);
			return this;
		}

		public ResultDataBuilder build(Integer code, String message) {
			response.put(CODE, code);
			response.put(MESSAGE, message);
			return this;
		}

		public ResultDataBuilder build(Integer code) {
			response.put(CODE, code);
			return this;
		}

		/**
		 * 完成构建
		 * 
		 * @author 顾继钞
		 * @date 2018年11月26日 下午2:29:11
		 * @return
		 */
		public Map<String, Object> build() {
			if (this.response.get(MESSAGE) == null) {
				// 成功
				if ((int) this.response.get(CODE) == 1) {
					this.response.put(MESSAGE, MESSAGE_DEFAULT_SUCCESS);
				} else {
					this.response.put(MESSAGE, MESSAGE_DEFAULT_FAIL);
				}
			}
			if (this.data != null && this.response.get(DATA) == null) {
				this.response.put(DATA, this.data);
			}
			return this.response;
		}

		/***
		 * @描述 完成构建,返回json
		 * @param
		 * @返回值  net.sf.json.JSONObject
		 * @创建人  顾继钞
		 * @创建时间  2019/1/11 11:09
		 */
		public JSONObject buildJson() {
			if (this.response.get(MESSAGE) == null) {
				// 成功
				if ((int) this.response.get(CODE) == 1) {
					this.response.put(MESSAGE, MESSAGE_DEFAULT_SUCCESS);
				} else {
					this.response.put(MESSAGE, MESSAGE_DEFAULT_FAIL);
				}
			}
			if (this.data != null && this.response.get(DATA) == null) {
				this.response.put(DATA, this.data);
			}
			return JSONObject.fromObject(this.response);
		}

		/**
		 * 放入参数
		 *
		 * @param key
		 * @param value
		 * @return
		 */
		public ResultDataBuilder appendToData(String key, Object value) {
			if (null == this.data){
				this.data = new HashMap<>(16);
			}
			this.data.put(key, value);
			return this;
		}
		
		/**
		 * 放入参数
		 * @author 顾继钞
		 * @date 2018年12月27日 下午4:27:08
		 * @param key
		 * @param value
		 * @return
		 */
		public ResultDataBuilder appendToOut(String key, Object value) {
			this.response.put(key, value);
			return this;
		}
	}

}
