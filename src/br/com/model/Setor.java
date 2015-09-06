package br.com.model;

public enum Setor {
	ADMINISTRADOR,
	CAIXA,
	GARCOM,
	COZINHEIRO;
	
	public static Setor get(String name) {
		for (Setor s : values()) {
			if(s.toString().equals(name)){
				return s; 
			}
		}
		return null;
	}
}
