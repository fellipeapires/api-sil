package br.com.sil.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PermissaoDto {
	private long idUsuario;
	private List<Long> listaIdPermissao;
}
