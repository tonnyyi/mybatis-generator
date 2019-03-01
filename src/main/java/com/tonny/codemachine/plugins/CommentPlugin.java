package com.tonny.codemachine.plugins;

import java.util.List;

import com.tonny.codemachine.support.BeanUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 注释生成插件
 * 使用数据库列注释作为对象属性的注释
 *
 * 注意: 表注释拿不到, 所以需要在生成的实体类上再手动写上注释
 *
 * @author <a href=mailto:ktyi@iflytek.com>伊开堂</a>
 * @date 2019/2/28 14:10
 */
public class CommentPlugin extends PluginAdapter {
    private static final Logger logger = LoggerFactory.getLogger(CommentPlugin.class);

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public void setContext(Context context) {
        MyCommentGenerator commentGenerator = new MyCommentGenerator();
        try {
            // 先执行一次生成CommentGenerator操作，然后再替换
            context.getCommentGenerator();
            BeanUtils.setProperty(context, "commentGenerator", commentGenerator);
        }
        catch (Exception e) {
            logger.warn("set context exception");
        }

        logger.info("comment plugin set context");
        super.setContext(context);
    }

    private static class MyCommentGenerator extends DefaultCommentGenerator {

        @Override
        public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
            // example field
            addComment(field, "addFieldComment");
        }

        private void addComment(JavaElement element, String... lines) {
            if (lines == null || lines.length == 0) {
                return;
            }

            element.addJavaDocLine("/**");
            for (String line : lines) {
                element.addJavaDocLine(" * " + line);
            }

            element.addJavaDocLine(" */");
        }

        @Override
        public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
            // bean field
            String remarks = introspectedColumn.getRemarks();
            if (remarks == null || remarks.trim().length() == 0) {
                remarks = introspectedTable.getFullyQualifiedTableNameAtRuntime() + "." + introspectedColumn.getActualColumnName();
            }
            addComment(field, remarks);
        }

        @Override
        public void addJavaFileComment(CompilationUnit compilationUnit) {
            super.addJavaFileComment(compilationUnit);
        }

        @Override
        public void addComment(XmlElement xmlElement) {
            xmlElement.addElement(new TextElement("<!-- addComment xmlElement -->"));
            // super.addComment(xmlElement);
        }

        @Override
        public void addRootComment(XmlElement rootElement) {
            super.addRootComment(rootElement);
        }

        @Override
        public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
            innerClass.addJavaDocLine("/**");
            innerClass.addJavaDocLine(" * addClassComment");
            innerClass.addJavaDocLine(" */");
            // super.addClassComment(innerClass, introspectedTable);
        }

        @Override
        public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
            innerClass.addJavaDocLine("/**");
            innerClass.addJavaDocLine("* addClassComment markAsDoNotDelete");
            innerClass.addJavaDocLine("*/");
            // super.addClassComment(innerClass, introspectedTable, markAsDoNotDelete);
        }

        @Override
        public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
            addComment(topLevelClass, introspectedTable.getRemarks());
        }

        @Override
        public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
            innerEnum.addJavaDocLine("/**");
            innerEnum.addJavaDocLine("* addEnumComment");
            innerEnum.addJavaDocLine("*/");
            // super.addEnumComment(innerEnum, introspectedTable);
        }

        @Override
        public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
            method.addJavaDocLine("/**");
            method.addJavaDocLine("* addGeneralMethodComment");
            method.addJavaDocLine("*/");
            // super.addGeneralMethodComment(method, introspectedTable);
        }

        @Override
        public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
            method.addJavaDocLine("/**");
            method.addJavaDocLine("* addGetterComment");
            method.addJavaDocLine("*/");
            // super.addGetterComment(method, introspectedTable, introspectedColumn);
        }

        @Override
        public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
            method.addJavaDocLine("/**");
            method.addJavaDocLine("* addSetterComment");
            method.addJavaDocLine("*/");
            // super.addSetterComment(method, introspectedTable, introspectedColumn);
        }
    }
}
