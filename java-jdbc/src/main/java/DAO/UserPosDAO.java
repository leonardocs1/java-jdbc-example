package DAO;

import java.sql.Connection;

import conexao.jdbc.SingleConnection;

public class UserPosDAO {

	private Connection connection;
	
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}
}

