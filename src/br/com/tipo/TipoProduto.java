package br.com.tipo;

public enum TipoProduto {
	ALMOCOBIFELIVRE,
	RODIZIOPIZZA,
	BIFEDESOPAS,
	LANCHES,
	PORCOES,
	SOBREMESA,
	SORVETE,
	BEBIDAS;
	
	public static TipoProduto get(String name) {
		for (TipoProduto s : values()) {
			if(s.toString().equals(name)){
				return s; 
			}
		}
		return null;
	}
}
