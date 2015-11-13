package br.com.tipo;

public enum StatusPedido {
	CAIXA, COZINHA, GARCOM, PROCESSANDO, CONCLUIDO;
	
	//COMPARA SE EXISTE
	public static StatusPedido get(String name){
		for(StatusPedido s : values()){
			if(s.toString().equals(name)){
				return s;
			}
		}
		return null;
	}
	
}
