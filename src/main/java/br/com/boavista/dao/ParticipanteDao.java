package br.com.boavista.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.boavista.domain.Participante;

public class ParticipanteDao {
	private final String SQL_INSERT = "INSERT INTO participantes ( " +
			" id , treinamento , nome , email , area) " +
			" VALUES (?, ?, ?, ?, ?)"; 
	
	private Connection connection;
	private Statement sqlDDL;
	private PreparedStatement sqlInsert;
	
	public ParticipanteDao() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection("jdbc:sqlite:treinamento.db");
			this.sqlDDL = this.connection.createStatement();
			
			sqlDDL.executeUpdate("drop table if exists participantes");
			sqlDDL.executeUpdate("create table participantes ( id integer, treinamento string, nome string, email string, area string)");

			this.sqlInsert = this.connection.prepareStatement(SQL_INSERT);

		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao carregar driver de conexão do banco de dados!\n" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Erro ao conectar ao banco de dados!\n" + e.getMessage());
		}
	}
	
	public void save(Participante participante) {
		try {
			this.sqlInsert.setInt(1, participante.getId());
			this.sqlInsert.setString(2, participante.getTreinamento());
			this.sqlInsert.setString(3, participante.getNome());
			this.sqlInsert.setString(4, participante.getEmail());
			this.sqlInsert.setString(5, participante.getArea());
			
			System.out.println("SqlInsert " + sqlInsert.toString());

			this.sqlInsert.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao salvar dados na base de dados!\n" + e.getMessage());
		}
	}
	
	public void cleanUp() {
		try {
			if (this.connection != null) {
				this.connection.close();	
			}
		} catch (Exception e) {
			System.out.println("Erro ao fechar conexão com o banco de dados!\n" + e.getMessage());
		}
	}
	
}
