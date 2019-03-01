package com.tonny.codemachine;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

import org.junit.Test;

/**
 * @author <a href=mailto:ktyi@iflytek.com>伊开堂</a>
 * @date 2019/3/1 09:49
 */
public class CommonTest {

    @Test
    public void test() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
        DatabaseMetaData metaData = connection.getMetaData();
        System.out.println(metaData);
    }

}
