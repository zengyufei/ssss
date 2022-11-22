package com.xunmo.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.solon.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.solon.plugins.inner.PaginationInnerInterceptor;
import com.xunmo.config.mybatisplus.XmLogicSqlInjector;
import com.xunmo.config.mybatisplus.XmMetaObjectHandlerImpl;
import com.xunmo.config.mybatisplus.XmMybatisStdOutImpl;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;

@Configuration
public class DbConfig {

    @Bean
    public void dbMasterCfg(@Db MybatisConfiguration cfg, @Db GlobalConfig globalConfig) {
        MybatisPlusInterceptor plusInterceptor = new MybatisPlusInterceptor();
        plusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        cfg.setCacheEnabled(false);
        cfg.addInterceptor(plusInterceptor);

        cfg.setLogImpl(XmMybatisStdOutImpl.class);
        globalConfig.setSqlInjector(new XmLogicSqlInjector());
        globalConfig.setMetaObjectHandler(new XmMetaObjectHandlerImpl());
    }


}
