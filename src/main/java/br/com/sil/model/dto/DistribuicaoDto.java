package br.com.sil.model.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class DistribuicaoDto {
	private long idLeitura;
	private long idRegional;
	private int grupoFaturamento;
	private long idUsuario;
	private long idUsuarioAtribuido;
	private String tarefa;
	private String endereco;
	private List<Long> listaIdLeitura;

	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataReferencia;
}
