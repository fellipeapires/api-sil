package br.com.sil.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class AcessoDto {
	private long idUsuario;
	private String browser;
	private String ip;
}
