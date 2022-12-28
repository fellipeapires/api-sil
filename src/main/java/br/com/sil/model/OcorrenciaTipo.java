package br.com.sil.model;

import lombok.Getter;

@Getter
public enum OcorrenciaTipo {
	INFORMATIVO("Informativo", 0),
	IMPEDIMENTO("Impedimento", 1);
	 
	private final String nome;
	private final int codigo;
	
	private OcorrenciaTipo(String nome, int codigo) {
		this.nome = nome;
		this.codigo = codigo;
	}
}
