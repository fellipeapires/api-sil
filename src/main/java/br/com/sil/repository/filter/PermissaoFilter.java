package br.com.sil.repository.filter;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class PermissaoFilter {
	private long id;
	private long idUsuario;
	private List<Long> listaIdPermissao;
	private int situacao;
}
