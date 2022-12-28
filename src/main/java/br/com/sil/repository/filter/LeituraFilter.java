package br.com.sil.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class LeituraFilter {
	private Long id;
	private Long idImportacao;
	private Long idRegional;
	private Integer grupoFaturamento;
	private String tarefa;
	private String instalacao;
	private String medidor;
	private Boolean isFoto;
	
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataReferencia;
}
