package br.com.model;

public class ItemPedido {

	private Integer codigo;
	private Produto produto;
	private Pedido pedido;

	public ItemPedido(Integer itemPedido, Produto produto, Pedido pedido) {
		this.codigo = itemPedido;
		this.produto = produto;
		this.pedido = pedido;
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
}
