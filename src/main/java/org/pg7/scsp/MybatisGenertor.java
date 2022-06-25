package org.pg7.scsp;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MybatisGenertor {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/scsp?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false",
                        "root", "962464HeLei")
                .globalConfig(builder -> {
                    builder.author("helei") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("/Users/helei/Desktop/SmartCampusServicePlatform/scsp/src/main/java/org/pg7/scsp"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("") // 设置父包名
                            .moduleName("genertor") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                                    "/Users/helei/Desktop/SmartCampusServicePlatform/scsp/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("tb_announce") // 设置需要生成的表名
                            .addTablePrefix("tb_", "c_"); // 设置过滤表前缀
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
