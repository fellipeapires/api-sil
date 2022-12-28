package br.com.sil.model;

import lombok.Getter;

@Getter
public enum CodigoExceptionSql {
	
	COD_UNIQUE_SQL("Unique", 2627);
	 
	private final String nome;
	private final int codigo;
	
	private CodigoExceptionSql(String nome, int codigo) {
		this.nome = nome;
		this.codigo = codigo;
	}

}
