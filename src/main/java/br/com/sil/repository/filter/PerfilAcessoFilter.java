package br.com.sil.repository.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class PerfilAcessoFilter {
	private long id;
	private String nome;
	private int situacao;
}
