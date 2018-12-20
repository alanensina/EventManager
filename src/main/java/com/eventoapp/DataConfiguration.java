package com.eventoapp;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;


@Configuration
public class DataConfiguration {

	// Bean de configuração de acesso ao banco de dados mySQL
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver"); // Define o JDBC do mySQL
		dataSource.setUrl("jdbc:mysql://localhost:3306/eventosapp"); // Endereço do banco de dados local juntamente do nome do banco
		dataSource.setUsername("root"); // Login de acesso ao banco de dados
		dataSource.setPassword("123"); // Senha de acesso ao banco de dados
		return dataSource;
	}

	// Bean de configuração do Hibernate
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL); // Definição de qual banco de dados será utilizado
		adapter.setShowSql(true); // Permite visualizar no console todos os comandos SQLs executados
		adapter.setGenerateDdl(true); // Permite que o Hibernate cria as tabelas automaticamente
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect"); // Define o dialeto utilizado
		adapter.setPrepareConnection(true); // Define que o Hibernate prepare a conexão e se conecte automaticamente
		return adapter;
	}
}
