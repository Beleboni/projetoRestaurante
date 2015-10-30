package br.com.factory;

import br.com.dao.FuncionarioDAO;
import br.com.dao.ItemPedidoDAO;
import br.com.dao.PedidoDAO;
import br.com.dao.ProdutoDAO;
import br.com.jdbc.FuncionarioJDBC;
import br.com.jdbc.ItemPedidoJDBC;
import br.com.jdbc.PedidoJDBC;
import br.com.jdbc.ProdutoJDBC;

public class DaoFactoryJDBC implements AbstractDAOFactory {

	@Override
	public FuncionarioDAO funcionarioDAO() {
		//CHAMANDO O FUNCIONARIOJDBC 
		return new FuncionarioJDBC();
	}

	@Override
	public ProdutoDAO produtoDAO() {
		// CHAMANDO O PRODUTOJDBC
		return new ProdutoJDBC();
	}

	@Override
	public PedidoDAO pedidoDAO() {
		// CHAMANDO O PEDIDOJDBC
		return new PedidoJDBC();
	}

	@Override
	public ItemPedidoDAO itemPedidoDAO() {
		// CHAMANDO O ITEMPEDIDOJDBC
		return new ItemPedidoJDBC();
	}
	
	

}
