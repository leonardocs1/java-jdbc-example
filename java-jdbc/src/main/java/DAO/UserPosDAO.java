package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.jdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class UserPosDAO {

	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Userposjava userposjava) {
		try {
			String sql = "insert into userposjava (nome, email) values (?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());
			insert.execute();
			connection.commit(); // salva no banco
		} catch (Exception e) {
			try {
				connection.rollback(); // reverte a operacao no banco
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public List<Userposjava> listar() {
		List<Userposjava> list = new ArrayList<Userposjava>();

		try {
			String sql = "select * from userposjava";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {
				Userposjava userposjava = new Userposjava();
				userposjava.setInd(resultado.getLong("id"));
				userposjava.setNome(resultado.getString("nome"));
				userposjava.setEmail(resultado.getString("email"));

				list.add(userposjava);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void salvarTelefone(Telefone telefone) {
		
		try {
			String sql = "INSERT INTO public.telefoneuser(numero, tipo, usuariopessoa)VALUES (?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getUsuario());
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public Userposjava buscar(Long id) {
		Userposjava retorno = new Userposjava();

		try {
			String sql = "select * from userposjava where id = " + id;
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {
				retorno.setInd(resultado.getLong("id"));
				retorno.setNome(resultado.getString("nome"));
				retorno.setEmail(resultado.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public List<BeanUserFone> listaUserFone (Long idUser) {
		
		List<BeanUserFone> beanUserFones = new ArrayList<>();
		
		String sql = "select  nome, numero, email from telefoneuser as fone ";
			sql += "inner join userposjava as userp ";
			sql += "on fone.usuariopessoa = userp.id ";
			sql += "where userp.id = " + idUser;
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				BeanUserFone userFone = new BeanUserFone();
				userFone.setNome(resultSet.getString("nome"));
				userFone.setNumero(resultSet.getString("numero"));
				userFone.setEmail(resultSet.getString("email"));
				beanUserFones.add(userFone);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return beanUserFones;
	}

	public void atualizar(Userposjava userposjava) {

		try {
			String sql = "update userposjava set nome = ? where id = " + userposjava.getInd();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userposjava.getNome());

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}

	}
	
	public void deletar(Long id) {
		try {
			String sql = "delete from userposjava where id = " + id;
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	

}
