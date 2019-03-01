package com.tonny.codemachine.plugins;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * 字段校验插件
 * 使用数据库字段限制(非空, 长度等)转成对应的注解, 加到Bean 属性上
 *
 * 工程中需要增加如下依赖:
 * <pre>
 *     <!-- 验证框架 API -->
 *     <dependency>
 *        <groupId>javax.validation</groupId>
 *        <artifactId>validation-api</artifactId>
 *        <version>2.0.1.Final</version>
 *        <optional>true</optional>
 *    </dependency>
 *     <!-- 验证框架实现 -->
 *    <dependency>
 *        <groupId>org.hibernate</groupId>
 *        <artifactId>hibernate-validator</artifactId>
 *        <version>6.0.14.Final</version>
 *        <optional>true</optional>
 *    </dependency>
 * </pre>
 *
 * @author <a href=mailto:ktyi@iflytek.com>伊开堂</a>
 * @date 2019/2/28 13:58
 */
public class ValidatePlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
            IntrospectedTable introspectedTable, ModelClassType modelClassType) {

        String modelName = introspectedTable.getTableConfiguration().getDomainObjectName();
        String fieldName = modelName + "." + field.getName();

        if (!introspectedColumn.isNullable()) {
            topLevelClass.addImportedType("javax.validation.constraints.NotNull");
            field.addAnnotation("@NotNull(message = \"" + fieldName + "字段值不能为空" + "\")");
        }

        if (introspectedColumn.isIdentity()) {
            topLevelClass.addImportedType("javax.validation.constraints.Positive");
            field.addAnnotation("@Positive(message = \"" + fieldName + "字段值应大于0" + "\")");
        }

        if (introspectedColumn.isStringColumn()) {
            int length = introspectedColumn.getLength();
            if (length > 0) {
                topLevelClass.addImportedType("javax.validation.constraints.Size");
                field.addAnnotation("@Size(max = " + length + ", message = \"" + fieldName + "字段值长度不能大于" + length + "\")");
            }
        }

        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }
}
