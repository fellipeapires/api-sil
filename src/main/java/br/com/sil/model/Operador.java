package br.com.sil.model;

import lombok.Getter;

@Getter
public enum Operador {
	NENHUM("Nenhum", 0),
	MENOR_IGUAL("MenorIgual", 1),
	MAIOR_IGUAL("MaiorIgual", 2);
	
	private final String nome;
	private final int codigo;
	
	private Operador(String nome, int codigo) {
		this.nome = nome;
		this.codigo = codigo;
	}
}
