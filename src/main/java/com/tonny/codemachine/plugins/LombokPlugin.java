package com.tonny.codemachine.plugins;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * 为实体 Bean 增加 Lombok 注解{@link lombok.Data @Data}
 *
 * 工程中要增加 Lombok 依赖:
 * <pre>
 *     <dependency>
 *        <groupId>org.projectlombok</groupId>
 *        <artifactId>lombok</artifactId>
 *        <version>[1.18.2,)</version>
 *        <optional>true</optional>
 *    </dependency>
 * </pre>
 *
 * @author <a href=mailto:ktyi@iflytek.com>伊开堂</a>
 * @date 2019/2/27 18:57
 */
public class LombokPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addLombokAnnotation(topLevelClass);
        return super.modelPrimaryKeyClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addLombokAnnotation(topLevelClass);
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addLombokAnnotation(topLevelClass);
        return super.modelRecordWithBLOBsClassGenerated(topLevelClass, introspectedTable);
    }

    private void addLombokAnnotation(TopLevelClass topLevelClass) {
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addImportedType("lombok.Data");
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
            IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
            IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

}
