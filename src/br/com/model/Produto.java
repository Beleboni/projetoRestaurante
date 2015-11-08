package br.com.model;

import br.com.tipo.StatusProduto;
import br.com.tipo.TipoProduto;

public class Produto {
	private Integer codigo;
	private TipoProduto tipoProduto;
	private Double valor;
	private String descricao;
	private StatusProduto status;
	
	public Produto() {

	}

	public Produto(Integer codigo, TipoProduto tipoProduto, Double valor,
			String descricao, StatusProduto status) {
		this.codigo = codigo;
		this.tipoProduto = tipoProduto;
		this.valor = valor;
		this.descricao = descricao;
		this.status = status;
	}

	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public StatusProduto getStatus() {
		return status;
	}
	public void setStatus(StatusProduto status) {
		this.status = status;
	}
}
