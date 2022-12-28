package br.com.sil.model;

import lombok.Getter;

@Getter
public enum DescricaoLog {
	IMPORTACAO_ARQUIVO_LEITURA("Importacao do arquivo de leitura", 1),
	EXPORTACAO_ARQUIVO_LEITURA("Exportacao do arquivo de leitura", 2),
	LANCAMENTO_LEITURA("Lancamento de Leitura", 3),
	LANCAMENTO_REPASSE("Lancamento de Repasse", 4),
	ALTERACAO_LEITURA("Alteracao de Leitura", 5),
	REVISAO_LEITURA("Revisao de Leitura", 6),
	INCLUSAO_FOTO("Inclusao de foto", 7),
	DOWNLOAD_FOTO("Download de foto", 8);
	
	private final String nome;
	private final int codigo;
	
	private DescricaoLog(String nome, int codigo) {
		this.nome = nome;
		this.codigo = codigo;
	}
}
