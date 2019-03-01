package com.tonny.codemachine.plugins;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * 为实体 Bean 增加 {@link java.io.Serializable} 接口实现,
 * 同时增加序列化版本号字段:
 * <pre>
 *     private static final long serialVersionUID = 1;
 * </pre>
 *
 * @author <a href=mailto:ktyi@iflytek.com>伊开堂</a>
 * @date 2019/2/28 13:59
 */
public class SerialPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addSerialField(topLevelClass);
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addSerialField(topLevelClass);
        return super.modelPrimaryKeyClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addSerialField(topLevelClass);
        return super.modelRecordWithBLOBsClassGenerated(topLevelClass, introspectedTable);
    }

    private void addSerialField(TopLevelClass topLevelClass) {
        topLevelClass.addSuperInterface(new FullyQualifiedJavaType("java.io.Serializable"));

        FullyQualifiedJavaType javaType = new FullyQualifiedJavaType("long");
        Field field = new Field("serialVersionUID", javaType);
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setStatic(true);
        field.setFinal(true);
        field.setInitializationString("1");

        topLevelClass.getFields().add(0, field);
    }
}
