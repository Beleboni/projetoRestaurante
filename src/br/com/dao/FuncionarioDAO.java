package br.com.dao;

import br.com.jdbc.crud.JdbcCRUD;
import br.com.model.Funcionario;


public interface FuncionarioDAO extends JdbcCRUD<Funcionario> {
	
	//LIGA��O ENTRE A CLASSE ESPECIFICOFUNCIONARIO PASSANDO O METODO FUNC�ES AO FUNCIONARIO

}
