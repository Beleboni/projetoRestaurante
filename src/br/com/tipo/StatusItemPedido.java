package br.com.tipo;

public enum StatusItemPedido {
	PROCESSANDO, ENTREGUE, CANCELADO;
	
	public static StatusItemPedido get(String name){
		for(StatusItemPedido s : values()){
			if(s.toString().equals(name)){
				return s;
			}
		}
		return null;
	}

}
