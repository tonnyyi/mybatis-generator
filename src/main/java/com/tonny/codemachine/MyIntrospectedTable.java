package com.tonny.codemachine;

import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

/**
 * @author <a href=mailto:ktyi@iflytek.com>伊开堂</a>
 * @date 2019/2/27 16:49
 */
public class MyIntrospectedTable extends IntrospectedTableMyBatis3Impl {

    public MyIntrospectedTable() {
        System.out.println(">>>>>>>>>>>>>>>> MyIntrospectedTable <<<<<<<<<<<<<<");
    }

    @Override
    public List<GeneratedJavaFile> getGeneratedJavaFiles() {
        return super.getGeneratedJavaFiles();
    }
}
