package br.com.model;

import java.time.LocalDate;


public class Pedido {
	private Integer codigo;
	private String mesa;
	private LocalDate dataPedido;
	private Boolean status = true;

	public Pedido(Integer codigo, String mesa, LocalDate dataPedido,Boolean status) {
		this.codigo = codigo;
		this.mesa = mesa;
		this.dataPedido = dataPedido;
		this.status = status;
	}

	public Pedido() {

	}

	public Pedido(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMesa() {
		return mesa;
	}

	public void setMesa(String mesa) {
		this.mesa = mesa;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	public boolean isProcesando(){
		return status;
	}
	
	public boolean isPronto(){
		return status;
	}


}
