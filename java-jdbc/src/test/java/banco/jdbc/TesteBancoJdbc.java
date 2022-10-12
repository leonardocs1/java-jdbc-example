package banco.jdbc;

import java.util.List;

import org.junit.Test;

import DAO.UserPosDAO;
import conexao.jdbc.SingleConnection;
import model.Userposjava;

public class TesteBancoJdbc {
	
	@Test
	public void initBanco () {
		UserPosDAO userPosDAO = new UserPosDAO();
		Userposjava userposjava = new Userposjava();
		
		userposjava.setInd(6L);
		userposjava.setNome("Paulo");
		userposjava.setEmail("paulo@email.com");
		
		userPosDAO.salvar(userposjava);
	}
	
	@Test
	public void initListar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<Userposjava> list = dao.listar();
			
			for (Userposjava userposjava: list) {
				System.out.println(userposjava);
				System.out.println("---------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void buscar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			Userposjava userposjava = new Userposjava();
			userposjava = dao.buscar(6L);
			System.out.println(userposjava);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
