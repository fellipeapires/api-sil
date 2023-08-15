package br.com.sil.model.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class LeituraDto {
	private Long id;
	private Long idRegional;
	private Integer grupoFaturamento;
	private String tarefaLeitura;
	private String tarefaEntrega;
	private String endereco;
	private String numero;
	private int opcaoAlteracaoTarefa;
	
	
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataReferencia;
}
