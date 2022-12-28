package br.com.sil.model;

import lombok.Getter;

@Getter
public enum StatusDistribuicao {
	NAO_LIBERADO("naoLiberado", 3),
	LIBERADO("liberado", 2),
	NO_COLETOR("noColetor", 1);
	
	private final String nome;
	private final int codigo;
	
	private StatusDistribuicao(String nome, int codigo) {
		this.nome = nome;
		this.codigo = codigo;
	}
}
