# 各种工具的学习

## hutool


## mapStruct

mapStruct需要在pom中配置依赖

```

<properties>
    <org.mapstruct.version>1.3.1.Final</org.mapstruct.version>
</properties>

<dependencies>
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct</artifactId>
        <version>${org.mapstruct.version}</version>
    </dependency>
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct-processor</artifactId>
        <version>${org.mapstruct.version}</version>
        <!--<optional>true</optional>不会再被其它项目所引用-->
        <scope>provided</scope>
    </dependency>
</dependencies>
```

原理是在编译时自动生成转换接口(BeanConverter)对应的实现文件(BeanConverterImpl) 1百万次映射10ms超高性能
