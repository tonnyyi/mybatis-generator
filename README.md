# 使用方式
1> 首先要把当前工程 install 到本地仓库, 在该工程根目录执行
```bash
mvn clean install
```

2> 在`generatorConfig.xml`中增加自定义插件配置, 从而开启响应的功能. 目前支持的插件有:

* `<plugin type="com.tonny.codemachine.plugins.SerialPlugin"/>` : 为 Java Bean 增加序列化接口实现
* `<plugin type="com.tonny.codemachine.plugins.LombokPlugin"/>` : 为 Java Bean 增加 Lombok 的`@Data`注解
* `<plugin type="com.tonny.codemachine.plugins.CommentPlugin"/>` : 为字段增加注释, 注释内容为字段的数据库注释
* `<plugin type="com.tonny.codemachine.plugins.ValidatePlugin"/>` : 为字段增加验证注解

3> 在`generatorConfig.xml`中正常配置数据库连接, 要生成的表信息等

4> 在该工程根目录执行 myabtis-generator 插件:
```bash
mvn mybatis-generator:generate
```
生成出来的文件在当前工程的`target/generated-sources/mybatis-generator`目录下

# 扩展方式
新增一个类, 继承`PluginAdaper`, 然后在`generatorConfig.xml`增加插件配置, 写上全路径

插件的完整生命周期参考官方文档: http://www.mybatis.org/generator/reference/pluggingIn.html

## 参考
https://github.com/itfsw/mybatis-generator-plugin