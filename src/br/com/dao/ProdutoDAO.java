package br.com.dao;

import java.util.List;

import br.com.jdbc.crud.JdbcCRUD;
import br.com.model.Produto;
import br.com.tipo.TipoProduto;

public interface ProdutoDAO extends JdbcCRUD<Produto>{
	List<Produto> listarPor(TipoProduto tipoProduto);
}
