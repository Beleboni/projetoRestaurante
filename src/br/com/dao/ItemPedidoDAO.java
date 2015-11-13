package br.com.dao;

import java.util.List;

import br.com.jdbc.crud.JdbcCRUD;
import br.com.model.ItemPedido;

public interface ItemPedidoDAO extends JdbcCRUD<ItemPedido> {
	
	List<ItemPedido> itensPedidoPorMesa(Integer mesa);
	
	void alterarStatus(ItemPedido itemPedido);
}
