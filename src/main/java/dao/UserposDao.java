package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class UserposDao {
	private Connection connection;

	public UserposDao() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Userposjava userposjava) {

		try {
			String sql = "insert into userposjava( nome, email) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			// TODO: handle exception
		}

	}

	public List<Userposjava> listar() throws SQLException {

		List<Userposjava> list = new ArrayList<Userposjava>();
		String sql = "select * from userposjava";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Userposjava userposjava = new Userposjava();
			userposjava.setId(resultSet.getLong("id"));
			userposjava.setNome(resultSet.getString("nome"));
			userposjava.setEmail(resultSet.getString("email"));

			list.add(userposjava);
		}

		return list;

	}

	public Userposjava buscar(Long id) throws SQLException {

		Userposjava retorno = new Userposjava();
		String sql = "select * from userposjava where id = " + id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			retorno.setId(resultSet.getLong("id"));
			retorno.setNome(resultSet.getString("nome"));
			retorno.setEmail(resultSet.getString("email"));

		}

		return retorno;

	}

	public void atualizar(Userposjava userposjava) {
		try {
			String sql = "update userposjava set nome = ? where id = " + userposjava.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userposjava.getNome());
			preparedStatement.execute();
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
			String sql = "delete from userposjava where id =  " + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

	public void salvarTelefone(Telefone telefone) {
		try {
			String sql = "insert into telefonesuser (numero, tipo, usuariopessoa) values(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, telefone.getNumero());
			preparedStatement.setString(2, telefone.getTipo());
			preparedStatement.setLong(3, telefone.getUsuario());
			preparedStatement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public List<BeanUserFone> listaUserFones(Long idUser) {
		List<BeanUserFone> beanUserFones = new ArrayList<>();
		
		String sql = "select nome, email, numero  from telefonesuser as fone inner join userposjava as userp on fone.usuariopessoa = userp.id where userp.id = "
				+ idUser;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				BeanUserFone beanUserFone = new BeanUserFone();
				beanUserFone.setNome(resultSet.getString("nome"));
				beanUserFone.setEmail(resultSet.getString("email"));
				beanUserFone.setNumero(resultSet.getString("numero"));
				// adicionar o objeto a lista
				beanUserFones.add(beanUserFone);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return beanUserFones;

	}

}
