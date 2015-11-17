package br.com.model;

import java.time.LocalDate;

import br.com.tipo.StatusSetor;
import br.com.tipo.StatusFuncionario;

public class Funcionario {
	private Integer codigo;
	private String nome;
	private String cpf;
	private String telefone;
	private StatusSetor statusSetor;
	private String usuario;
	private String senha;
	private LocalDate admissao;
	private StatusFuncionario status;

	// CONSTRUTOR VAZIO
	public Funcionario() {

	}

	// CONSTRUTOR CHEIO
	public Funcionario(Integer codigo, String nome, String cpf,
			String telefone, StatusSetor statusSetor, String usuario,
			String senha, LocalDate admissao, StatusFuncionario status) {
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.statusSetor = statusSetor;
		this.usuario = usuario;
		this.senha = senha;
		this.admissao = admissao;
		this.status = status;
	}

	// GETS E SETTERS
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public StatusSetor getSetor() {
		return statusSetor;
	}

	public void setSetor(StatusSetor statusSetor) {
		this.statusSetor = statusSetor;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getAdmissao() {
		return admissao;
	}

	public void setAdmissao(LocalDate admissao) {
		this.admissao = admissao;
	}

	public StatusFuncionario getStatus() {
		return status;
	}

	public void setStatus(StatusFuncionario status) {
		this.status = status;
	}

	public boolean verificaSenha(String senha) {
		return this.senha.equals(senha);
	}

}
