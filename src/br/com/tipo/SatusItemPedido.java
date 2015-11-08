package br.com.tipo;

public enum SatusItemPedido {
	ENTREGUE, CANCELADO;
	
	public static SatusItemPedido get(String name){
		for(SatusItemPedido s : values()){
			if(s.toString().equals(name)){
				return s;
			}
		}
		return null;
	}

}
