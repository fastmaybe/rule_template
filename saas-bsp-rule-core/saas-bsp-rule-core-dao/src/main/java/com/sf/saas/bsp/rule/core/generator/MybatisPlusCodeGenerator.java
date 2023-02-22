package com.sf.saas.bsp.rule.core.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 01407460
 * @date 2022/9/5 14:15
 */
public class MybatisPlusCodeGenerator {

    //表前缀 和模块名称一致
    private static String TABLE_PRE ="bsp_";


//    private static String[] tables = {"bsp_rule_tenant_from_template"};
    private static String[] tables = {""};

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setAuthor("01408885");
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        //覆盖 默认为false
        gc.setFileOverride(true);

        // 设置 resultMap
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://10.148.246.66:3306/sabsp?useUnicode=true&ampcharacterEncoding=utf8&ampallowMultiQueries=true&ampserverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("sabps");
        dsc.setPassword("sabps_##AAA");
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                System.out.println("转换类型：" + fieldType);
                //tinyint转换成INTEGER
                if (fieldType.toLowerCase().contains("tinyint")) {
                    return DbColumnType.INTEGER;
                }
                if (fieldType.toLowerCase().contains("timestamp")) {
                    return DbColumnType.LONG;
                }
                return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
            }
        });
        mpg.setDataSource(dsc);

        //包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.sf.saas.bsp.rule");
        pc.setEntity("core.dao.entity");
        pc.setMapper("core.dao.mapper");
        pc.setService("core.service.crud");
        pc.setServiceImpl("core.service.crud.impl");
        pc.setController("controller");
        pc.setPathInfo(Collections.emptyMap()); //禁止在根路径重复生成
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 写于父类中的公共字段


        //表名称
        strategy.setInclude(tables);


        //驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //去掉序列化
        strategy.setEntitySerialVersionUID(false);
        strategy.setTablePrefix(TABLE_PRE );
        mpg.setStrategy(strategy);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        //定义生成的文件路径
        List<FileOutConfig> focList = configFilePath(projectPath);
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);



        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();

    }





    private static List<FileOutConfig> configFilePath(String projectPath) {
        System.out.println("projectPath:" + projectPath);
        List<FileOutConfig> focList = new ArrayList<>();
        // 如果模板引擎是 velocity
        String xmlTemplatePath = "/templates/mapper.xml.vm";
        String entityTemplatePath = "/templates/entity.java.vm";
        String mapperTemplatePath = "/templates/mapper.java.vm";
        String serviceTemplatePath = "/templates/service.java.vm";
        String serviceImplTemplatePath = "/templates/serviceImpl.java.vm";
//        String controllerTemplatePath = "/templates/controller.java.vm";
        String baseJavaPackageFile = "/src/main/java/com/sf/saas/bsp/rule";
        // mapper.xml
        focList.add(new FileOutConfig(xmlTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/saas-bsp-rule/saas-bsp-rule-core/saas-bsp-rule-core-dao/src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        // entity.java
        focList.add(new FileOutConfig(entityTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/saas-bsp-rule/saas-bsp-rule-core/saas-bsp-rule-core-dao" + baseJavaPackageFile + "/core/dao/entity"
                        + "/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });
        // mapper.java
        focList.add(new FileOutConfig(mapperTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/saas-bsp-rule/saas-bsp-rule-core/saas-bsp-rule-core-dao" + baseJavaPackageFile + "/core/dao/mapper"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_JAVA;
            }
        });
        // service.java
        focList.add(new FileOutConfig(serviceTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/saas-bsp-rule/saas-bsp-rule-core/saas-bsp-rule-core-service" + baseJavaPackageFile + "/core/service/crud"
                        + "/" + "I" + tableInfo.getEntityName() + "Service" + StringPool.DOT_JAVA;
            }
        });
        // serviceImpl.java
        focList.add(new FileOutConfig(serviceImplTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/saas-bsp-rule/saas-bsp-rule-core/saas-bsp-rule-core-service" + baseJavaPackageFile + "/core/service/crud/impl"
                        + "/" + tableInfo.getEntityName() + "ServiceImpl" + StringPool.DOT_JAVA;
            }
        });
        // controller.java
//        focList.add(new FileOutConfig(controllerTemplatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return projectPath + "/saas-iss-eas-business" + baseJavaPackageFile + "/controller"
//                        + "/" + tableInfo.getEntityName() + "Controller" + StringPool.DOT_JAVA;
//            }
//        });

        return focList;
    }


}
