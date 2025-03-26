package com.bookcatalog.config;

import com.bookcatalog.dao.BookDao;
import org.jooq.SQLDialect;
import org.jooq.conf.*;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DBConfig {

  @Bean
  public DefaultDSLContext dsl(@Autowired DefaultConfiguration configuration) {
    return new DefaultDSLContext(configuration);
  }

  @Bean
  public DefaultConfiguration configuration(@Autowired DataSource dataSource,
                                            @Value("${spring.jooq.sql-dialect}") SQLDialect sqlDialect) {
    Settings settings = SettingsTools.defaultSettings();
//    settings.setRenderKeywordStyle(RenderKeywordStyle.LOWER);
    settings.setRenderQuotedNames(RenderQuotedNames.NEVER);
//    settings.setRenderNameCase(RenderNameCase.LOWER);
    DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
    jooqConfiguration.set(dataSource);
    jooqConfiguration.setSQLDialect(sqlDialect);
    jooqConfiguration.set(settings);
    return jooqConfiguration;
  }

  @Bean
  public TransactionManager transactionManager(@Autowired DataSource dataSource) {
    return new JdbcTransactionManager(dataSource);
  }

  @Bean
  BookDao bookDao(@Autowired DefaultDSLContext dsl) {
    return new BookDao(dsl);
  }
}
