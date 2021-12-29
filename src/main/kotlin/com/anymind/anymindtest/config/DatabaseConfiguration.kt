package com.anymind.anymindtest.config

import javax.sql.DataSource
import liquibase.integration.spring.SpringLiquibase
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(LiquibaseProperties::class)
class DatabaseConfiguration {

    @Bean
    fun liquibase(
        dataSource: DataSource,
        dataSourceProperties: DataSourceProperties,
        liquibaseProperties: LiquibaseProperties
    ): SpringLiquibase {
        val liquibase = SpringLiquibase()
        liquibase.dataSource = dataSource
        liquibase.changeLog = "classpath:config/liquibase/master.xml"
        return liquibase
    }
}
