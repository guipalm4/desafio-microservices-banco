package com.guiPalma.desafio.core.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DbConfigServervice {

	// private ParametroLimiteRepository parametroLimiteRepository;

	public void criaBDConta() {

		try {
			String databaseName = "conta_corrente_db";
			String userName = "root";
			String password = "root123";

			String url = "jdbc:mysql://localhost:3308/?allowPublicKeyRetrieval=true&sslMode=DISABLED";

			Connection connection = DriverManager.getConnection(url, userName, password);
			String sql = "CREATE DATABASE IF NOT EXISTS " + databaseName;

			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void criaBDCadastro() {

		try {
			String databaseName = "cadastro_pessoa_db";
			String userName = "root";
			String password = "root123";

			String url = "jdbc:mysql://localhost:3308/?allowPublicKeyRetrieval=true&sslMode=DISABLED";

			Connection connection = DriverManager.getConnection(url, userName, password);
			String sql = "CREATE DATABASE IF NOT EXISTS " + databaseName;

			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void instantiateTestDatabase() {
		criaBDConta();
		criaBDCadastro();		
	}

}
