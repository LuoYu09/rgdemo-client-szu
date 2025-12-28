package com.szu.config;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import jakarta.annotation.PostConstruct;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class MybatisConfig {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @PostConstruct
    public void registerTypeHandlers() {
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            TypeHandlerRegistry typeHandlerRegistry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();

            // 注册List类型的处理器
            typeHandlerRegistry.register(List.class, JacksonTypeHandler.class);
            typeHandlerRegistry.register(Map.class, JacksonTypeHandler.class);
        }
    }
}
