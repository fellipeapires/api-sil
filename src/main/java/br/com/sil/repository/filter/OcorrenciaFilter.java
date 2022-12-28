package br.com.sil.repository.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class OcorrenciaFilter {
	private long id;
	private Integer codigo;
	private int situacao;
}
