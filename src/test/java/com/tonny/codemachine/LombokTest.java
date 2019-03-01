package com.tonny.codemachine;

import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * @author <a href=mailto:ktyi@iflytek.com>伊开堂</a>
 * @date 2019/2/28 15:09
 */
public class LombokTest {

    @Test
    public void test() throws Exception {
        ArrayList<String> warnings = new ArrayList<>();
        Configuration configuration = new ConfigurationParser(warnings)
                .parseConfiguration(Resources.getResourceAsStream("generatorConfig.xml"));
        new MyBatisGenerator(configuration, new DefaultShellCallback(true), warnings).generate(null, null, null, false);
    }

}
