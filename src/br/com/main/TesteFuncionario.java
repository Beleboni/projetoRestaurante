package br.com.main;

import br.com.dao.FuncionarioDAO;
import br.com.jdbc.FuncionarioJDBC;
import br.com.model.Funcionario;

public class TesteFuncionario {
	
	public static void main(String[] args) {
		FuncionarioDAO funcionarioDao = new FuncionarioJDBC();
	
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("Paulo de Oliveira");
		funcionario.setCpf("12345678965");
		funcionario.setTelefone(12345678);
		//funcionario.setSetor("Cozinheiro");
		funcionario.setUsuario("Souza");
		funcionario.setSenha("5678");
		
		//especificoFuncionario.inserir(funcionario);
	
		//especificoFuncionario.alterar(funcionario);
		
		//funcionario.setCodigo(1);
		//especificoFuncionario.excluir(funcionario);
	
		//System.out.println(funcionarioDao.buscar(3).getSetor());
		
		funcionarioDao.todos().forEach(f -> System.out.println(f.getSetor()));
		
	}
	

}
