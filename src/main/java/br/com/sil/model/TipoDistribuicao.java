package br.com.sil.model;

import lombok.Getter;

@Getter
public enum TipoDistribuicao {
	TAREFA("Tarefa", 1),
	ENDERECO("Endereco", 2),
	INDIVIDUAL("Individual", 3);
	
	private final String nome;
	private final int codigo;
	
	private TipoDistribuicao(String nome, int codigo) {
		this.nome = nome;
		this.codigo = codigo;
	}
}
