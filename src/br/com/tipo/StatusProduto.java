package br.com.tipo;

public enum StatusProduto {
	ATIVO, INATIVO;
	
	public static StatusProduto get(String name) {
		for (StatusProduto s : values()) {
			if(s.toString().equals(name)){
				return s; 
			}
		}
		return null;
	}

}
