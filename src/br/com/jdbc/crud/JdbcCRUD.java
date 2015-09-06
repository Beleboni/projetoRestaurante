package br.com.jdbc.crud;

import java.util.List;

public interface JdbcCRUD<T> {
	void inserir(T entidade);
	void alterar(T entidade);
	void excluir(T entidade);
	T buscar(Integer id);
	List<T> todos();
}
