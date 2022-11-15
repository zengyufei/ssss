package com.xunmo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.solon.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.solon.plugins.inner.PaginationInnerInterceptor;
import com.xunmo.config.mybatisplus.MetaObjectHandlerImpl;
import com.xunmo.config.mybatisplus.MyLogicSqlInjector;
import com.xunmo.config.mybatisplus.MybatisStdOutImpl;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import javax.sql.DataSource;

@Configuration
public class DbConfig {
    @Bean(value = "db1", typed = true)
    public DataSource db1(@Inject("${test.db1}") HikariDataSource ds) {
        return ds;
    }

    @Bean
    public void db1_cfg(@Db("db1") MybatisConfiguration cfg, @Db("db1") GlobalConfig globalConfig) {
        MybatisPlusInterceptor plusInterceptor = new MybatisPlusInterceptor();
        plusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        cfg.setCacheEnabled(false);
        cfg.addInterceptor(plusInterceptor);
        cfg.setLogImpl(MybatisStdOutImpl.class);

        globalConfig.setSqlInjector(new MyLogicSqlInjector());
        globalConfig.setMetaObjectHandler(new MetaObjectHandlerImpl());

    }
}
