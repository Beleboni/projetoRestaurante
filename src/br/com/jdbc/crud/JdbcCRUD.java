package br.com.jdbc.crud;

import java.util.List;

import br.com.exception.UsoException;

public interface JdbcCRUD<T> {
	void inserir(T entidade) throws UsoException;
	void alterar(T entidade);
	void excluir(T entidade);
	T buscar(Integer id);
	List<T> todos();
}
