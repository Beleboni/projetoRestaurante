package br.com.factory;

import br.com.dao.FuncionarioDAO;
import br.com.dao.ItemPedidoDAO;
import br.com.dao.PedidoDAO;
import br.com.dao.ProdutoDAO;

public interface AbstractDAOFactory {
	
	FuncionarioDAO funcionarioDAO();
	ProdutoDAO produtoDAO();
	PedidoDAO pedidoDAO();
	ItemPedidoDAO itemPedidoDAO();
	
}
