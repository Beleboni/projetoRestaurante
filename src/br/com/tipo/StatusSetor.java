package br.com.tipo;

public enum StatusSetor {
	ADMINISTRADOR,
	CAIXA,
	GARCOM,
	COZINHEIRO;
	
	//COMPARA SE EXISTE
	public static StatusSetor get(String name) {
		for (StatusSetor s : values()) {
			if(s.toString().equals(name)){
				return s; 
			}
		}
		return null;
	}
}
