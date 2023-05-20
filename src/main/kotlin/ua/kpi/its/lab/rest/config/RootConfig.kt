package ua.kpi.its.lab.rest.config

import jakarta.persistence.EntityManagerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import java.util.*
import javax.sql.DataSource

@Configuration
@ComponentScan(basePackages = ["ua.kpi.its.lab.rest"])
@EnableJpaRepositories(basePackages = ["ua.kpi.its.lab.rest.repository"])
class RootConfig {
    @Bean
    fun dataSource(): DataSource =
        EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .build()

    @Bean
    fun entityManagerFactory(dataSource: DataSource) =
        LocalContainerEntityManagerFactoryBean().apply {
            setDataSource(dataSource)
            setPackagesToScan("ua.kpi.its.lab.rest.entity")
            jpaVendorAdapter = HibernateJpaVendorAdapter()
            setJpaProperties(additionalProperties())
        }

    @Bean
    fun transactionManager(entityManagerFactory: LocalContainerEntityManagerFactoryBean): PlatformTransactionManager =
        JpaTransactionManager(entityManagerFactory.`object` as EntityManagerFactory)

    @Bean
    fun exceptionTranslation() =
        PersistenceExceptionTranslationPostProcessor()

    private fun additionalProperties(): Properties =
        Properties().apply {
            setProperty("hibernate.hbm2ddl.auto", "create")
            setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
            setProperty("show_sql", "true")
        }
}