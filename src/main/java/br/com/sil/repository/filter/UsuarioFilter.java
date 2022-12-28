package br.com.sil.repository.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UsuarioFilter {
	private String nome;
	private String login;
	private long idRegional;
	private long idPerfilAcesso;
	private int situacao;
}