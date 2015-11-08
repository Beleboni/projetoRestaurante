package br.com.model;

import br.com.tipo.SatusItemPedido;

public class ItemPedido {

	private Integer codigo;
	private Produto produto;
	private Pedido pedido;
	private SatusItemPedido status;

	public ItemPedido(Integer itemPedido, Produto produto, Pedido pedido, SatusItemPedido status) {
		this.codigo = itemPedido;
		this.produto = produto;
		this.pedido = pedido;
		this.status = status;
	}

	public ItemPedido() {

	}

	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public SatusItemPedido getStatus() {
		return status;
	}
	public void setStatus(SatusItemPedido status) {
		this.status = status;
	}
}
