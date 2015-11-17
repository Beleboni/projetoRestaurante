package br.com.dao;

import java.util.List;

import br.com.jdbc.crud.JdbcCRUD;
import br.com.model.Pedido;

public interface PedidoDAO extends JdbcCRUD<Pedido>{
	
	Integer ultimoPedidoId();
	List<Pedido> pedidoEmProcesso();
	List<Pedido> pedidoPronto();
	List<Pedido> todosPedidosConcluidos();
	boolean isOcupada(Integer mesa);

}
