package com.gjc.generator;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.gjc.controller.TableController;
import com.gjc.controller.TableToDao;
import com.gjc.entity.ConnectInfo;
import com.gjc.entity.Replace;
import com.gjc.entity.TableInfo;
import com.gjc.util.StringUtil;

public class Application {

	public static void main(String[] args) {
		try {

			Properties p = new Properties();

			// p.setProperty("file.resource.loader.class",
			// "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			// 定义字符集
			p.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
			p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
			// 初始化Velocity引擎，指定配置Properties
			Velocity.init(p);

			// 创建文件夹
			new Replace();
			File controllerDir = new File("src/main/java/" + StringUtil.getPackage(Replace.getControllerPackage()));
			File serviceImplDir = new File("src/main/java/" + StringUtil.getPackage(Replace.getServiceImplPackage()));
			File serviceDir = new File("src/main/java/" + StringUtil.getPackage(Replace.getServicePackage()));
			File mapperDir = new File("src/main/java/" + StringUtil.getPackage(Replace.getDaoPackage()));
			File entityDir = new File("src/main/java/" + StringUtil.getPackage(Replace.getEntityPackage()));
			if (!controllerDir.exists()) {
				controllerDir.mkdirs();
			}
			if (!serviceDir.exists()) {
				serviceDir.mkdirs();
			}
			if (!serviceImplDir.exists()) {
				serviceImplDir.mkdirs();
			}
			if (!mapperDir.exists()) {
				mapperDir.mkdirs();
			}
			if (!entityDir.exists()) {
				entityDir.mkdirs();
			}

			// 获取表结构信息
			new ConnectInfo();
			Map<String, List<TableInfo>> tableMap = TableController.backTablesInfo();
			Set<String> set = tableMap.keySet();
			String AllClomun = ConnectInfo.getTableClomn();
			String[] tableCloumn = AllClomun.split("\\|");
			int i = 0;
			for (String table : set) {
				VelocityContext context = new VelocityContext();
				// 获取公有替换信息
				Set<String> setReplace = Replace.getMap().keySet();
				for (String key : setReplace) {
					context.put(key, Replace.getMap().get(key));
					System.out.printf("%s\t%s\n", key, Replace.getMap().get(key));
				}
				String tableEntity = StringUtil.replaceUnderLineAndUpperCase(table);
				context.put("Entity", tableEntity);
				context.put("tableEntity", table);
				context.put("entity", StringUtil.firstToLower(tableEntity));

				// 获取dao层替换信息(数据库字段名,对象字段名，字段类型)
				List<TableToDao> tableDaoList = TableToDao.getDaoInfo(tableMap.get(table));
				List<TableToDao> partTableDaoList = TableToDao.getDaoInfo(tableMap.get(table));
//				for (TableToDao ttd : partTableDaoList) {
//
//				}
				context.put("entityId", tableDaoList.get(0).getColumn());
				TableToDao tableDao = new TableToDao();
				context.put("tableDao", tableDao);
				context.put("tableDaoList", tableDaoList);
				Template templateEntity = Velocity.getTemplate("src/main/resources/template/TemplateEntity.vm",
						"UTF-8");
				FileWriter writerEntity = new FileWriter(entityDir.getPath() + "/" + tableEntity + ".java");
				templateEntity.merge(context, writerEntity);
				writerEntity.close();

				Template templateEntityExample = Velocity
						.getTemplate("src/main/resources/template/TemplateEntityExample.vm", "UTF-8");
				FileWriter writerEntityExample = new FileWriter(
						entityDir.getPath() + "/" + tableEntity + "Example.java");
				templateEntityExample.merge(context, writerEntityExample);
				writerEntityExample.close();

				tableDaoList.remove(0);
				partTableDaoList.remove(0);
				context.put("tableDaoList", tableDaoList);
				context.put("partTableDaoList", partTableDaoList);

				// 获取模版
				Template template = Velocity.getTemplate("src/main/resources/template/Template.vm", "UTF-8");
				// 流
				FileWriter writer = new FileWriter(controllerDir.getPath() + "/" + tableEntity + "V3Controller.java");
				// 合并
				template.merge(context, writer);
				// 11.必须关闭流，写入内容
				writer.close();

				Template templateService = Velocity.getTemplate("src/main/resources/template/TemplateService.vm",
						"UTF-8");
				FileWriter writerService = new FileWriter(serviceDir.getPath() + "/" + tableEntity + "V3Service.java");
				templateService.merge(context, writerService);
				writerService.close();

				Template templateServiceImpl = Velocity
						.getTemplate("src/main/resources/template/TemplateServiceImpl.vm", "UTF-8");
				FileWriter writerServiceImpl = new FileWriter(
						serviceImplDir.getPath() + "/" + tableEntity + "V3ServiceImpl.java");
				templateServiceImpl.merge(context, writerServiceImpl);
				writerServiceImpl.close();

				Template templateMapperXML = Velocity.getTemplate("src/main/resources/template/TemplateMapperXML.vm",
						"UTF-8");
				FileWriter writerMapperXML = new FileWriter(mapperDir.getPath() + "/" + tableEntity + "Mapper.xml");
				templateMapperXML.merge(context, writerMapperXML);
				writerMapperXML.close();

				Template templateMapper = Velocity.getTemplate("src/main/resources/template/TemplateMapper.vm",
						"UTF-8");
				FileWriter writerMapper = new FileWriter(mapperDir.getPath() + "/" + tableEntity + "Mapper.java");
				templateMapper.merge(context, writerMapper);
				writerMapper.close();

				i++;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
