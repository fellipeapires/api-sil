package br.com.sil.repository.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class EmpresaFilter {
	private long id;
	private int situacao;
}
