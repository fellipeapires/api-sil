package br.com.sil.model;

import lombok.Getter;

@Getter
public enum StatusRegitro {

	INATIVO("Inativo", 0), 
	ATIVO("Ativo", 1),
	TODOS("Todos", 2); 

	private final String nome;
	private final int codigo;

	private StatusRegitro(String nome, int codigo) {
		this.nome = nome;
		this.codigo = codigo;
	}

}
