package com.aperture.member;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

/**
 * 主要用于根据表来自动化生成Controller、Service、Module、Mapper。
 * 需要注意的是，Service生成的方法会存在如下类：PageParam、xxxxVO、xxxxParam，可自行更改设置，或者新建这些类即可
 *
 * @author HALOXIAO
 * @since 2020-09-23 10:34
 */

public class CodeGenerator {

    /**
     * 代码生成器所需配置，以core-service为例
     */
    private static final String MYSQL_URL = "jdbc:mysql://127.0.0.1:3306/aperture-member?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASSWORD = "root";
    private static final String PACKAGE_PATH = "com.aperture.community.member";//改成自己的包名
    private static final String OUTPUT_PATH = "/community/aperture-member/src/main";//一般来说，将前面的core-service改为自己模块的就好了
    private static final String SERVICE_TEMPLATE = "/template/Service.java.vm"; //这个一般不用改
    private static final String AUTHOR = "JavaJayV";


    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入").append(tip).append("：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * RUN THIS
     */
    @Test
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + OUTPUT_PATH);
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        gc.setIdType(IdType.NONE);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(MYSQL_URL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(MYSQL_USER);
        dsc.setPassword(MYSQL_PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent(PACKAGE_PATH);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        mpg.setCfg(cfg);
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setService(SERVICE_TEMPLATE);
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);


        // 策略配
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(scanner("表名"));
        strategy.setSuperEntityColumns("id");
        strategy.setRestControllerStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.setEntityTableFieldAnnotationEnable(true);

        mpg.setStrategy(strategy);
        // 选择 Velocity 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }

}