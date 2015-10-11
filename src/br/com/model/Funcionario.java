package br.com.model;

import java.time.LocalDate;

import br.com.tipo.Setor;

public class Funcionario {
	private Integer codigo;
	private String nome;
	private String cpf;
	private String telefone;
	private Setor setor;
	private String usuario;
	private String senha;
	private LocalDate admissao;
	private Boolean status = true;
	
	//CONSTRUTOR VAZIO
	public Funcionario() {
		
	}
	//CONSTRUTOR CHEIO
	public Funcionario(Integer codigo, String nome, String cpf,
			String telefone, Setor setor, String usuario, String senha, LocalDate admissao, Boolean status) {
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.setor = setor;
		this.usuario = usuario;
		this.senha = senha;
		this.admissao = admissao;
		this.status = status;
	}
	
	//GETS E SETTERS
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
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
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
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
}
