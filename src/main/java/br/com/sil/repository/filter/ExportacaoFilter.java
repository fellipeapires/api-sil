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
public class ExportacaoFilter {
	private long id;
	private Long idGrupoFaturamento;
	private Integer grupoFaturamento;
	private Long idRegional;
	private Long idUsuario;
	private String tipoLeitura;
	private Boolean isDataFim;
	
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataReferencia;
}
