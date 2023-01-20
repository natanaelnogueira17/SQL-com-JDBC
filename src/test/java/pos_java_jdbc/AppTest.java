package pos_java_jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import dao.UserposDao;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

/**
 * Unit test for simple App.
 */
public class AppTest {
	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void initBanco() {
		UserposDao userposDao = new UserposDao();
		Userposjava userposjava = new Userposjava();

		userposjava.setNome("mateus viana");
		userposjava.setEmail("EMAIL.com");

		userposDao.salvar(userposjava);

	}

	@Test
	public void initListar() {
		UserposDao dao = new UserposDao();
		try {
			List<Userposjava> list = dao.listar();
			for (Userposjava item : list) {
				System.out.println(item);
				System.out.println("---------------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initBuscar() {
		UserposDao dao = new UserposDao();
		try {
			Userposjava userposjava = dao.buscar(2L);
			System.out.println(userposjava);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initAtualizar() {

		try {
			UserposDao dao = new UserposDao();
			Userposjava userposjava = dao.buscar(3L);
			userposjava.setNome("mateus viana");

			dao.atualizar(userposjava);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	public void initDeletar() {
		try {
			UserposDao dao = new UserposDao();
			dao.deletar(7L);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void telefoneInsert() {
			UserposDao dao =  new UserposDao();
			Telefone telefone =  new Telefone();
			
			telefone.setNumero("85 985967485");
			telefone.setTipo("celular");
			telefone.setUsuario(10L);
			dao.salvarTelefone(telefone);
		
	}
	@Test
	public void testeCarregarFonesUser() {
		UserposDao dao =  new UserposDao();
		List<BeanUserFone>beanUserFones =dao.listaUserFones(8L);
		for(BeanUserFone item : beanUserFones) {
			System.out.println(item);
			System.out.println("---------------------------------------------------------");
		}
		
	}
	

}
