package br.com.factory;

import br.com.dao.FuncionarioDAO;
import br.com.dao.ItemPedidoDAO;
import br.com.dao.PedidoDAO;
import br.com.dao.ProdutoDAO;

public class DaoFactory {
	
	private static DaoFactory factory;
	private static AbstractDAOFactory daoFactory;
	
	//CONSTRUINDO A FABRICA DE DAO
	public static DaoFactory getDaoFactory(){
		if (factory == null) {
			factory = new DaoFactory();			
		}
		daoFactory = new DaoFactoryJDBC();
		return factory;
	}
	
	//DISPONIBILISANDO O DAO A FACTORY
	public FuncionarioDAO funcionarioDao(){
		return daoFactory.funcionarioDAO();
	}
	public ProdutoDAO produtoDao(){
		return daoFactory.produtoDAO();
	}
	public PedidoDAO pedidoDAO(){
		return daoFactory.pedidoDAO();
	}
	public ItemPedidoDAO itemPedidoDAO(){
		return daoFactory.itemPedidoDAO();
	}
	

}
