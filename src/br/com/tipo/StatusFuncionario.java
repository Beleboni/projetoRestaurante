package br.com.tipo;

public enum StatusFuncionario {
	ATIVO, INATIVO;

	public static StatusFuncionario get(String name){
		for(StatusFuncionario s : values()){
			if(s.toString().equals(name)){
				return s;
			}
		}
		return null;
	}
}
