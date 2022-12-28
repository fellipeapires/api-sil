package br.com.sil.model;

import lombok.Getter;

@Getter
public enum RetornoLeituraInfo {
	SEM_FOTO("semFoto", 0),
	COM_FOTO("comFoto", 1),
	NAO_ANALIZADO("naoAnalizado", 0),
	ANALIZADO("analizado", 1);
	
	private final String nome;
	private final int codigo;
	
	private RetornoLeituraInfo(String nome, int codigo) {
		this.nome = nome;
		this.codigo = codigo;
	}
}
