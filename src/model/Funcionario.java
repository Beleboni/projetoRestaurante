package model;

public class Funcionario {
	private Integer codigo;
	private String nome;
	private String cpf;
	private Integer telefone;
	private String setor;
	private String usuario;
	private String senha;
	
	//CONSTRUTOR VAZIO
	public Funcionario() {
		
	}
	//CONSTRUTOR CHEIO
	public Funcionario(Integer codigo, String nome, String cpf,
			Integer telefone, String setor, String usuario, String senha) {
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.setor = setor;
		this.usuario = usuario;
		this.senha = senha;
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
	public Integer getTelefone() {
		return telefone;
	}
	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
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
	
	
	
}
