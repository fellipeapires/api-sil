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
public class RetornoLeituraFilter {
	private Long id;
	private Long idLeitura;
	private Long idRegional;
	private Long idGrupoFaturamento;
	private Long idUsuario;
	private Long idOcorrencia;
	private Integer ocorrencia;
	private Integer grupoFaturamento;
	private String tarefa;
	private String instalacao;
	private String medidor;
	private String segmento;
	private String msgMobile;
	private Integer operador;
	private String tipoVariacao;
	private Double variacaoLeitura;
	private Integer ativo;
	private Integer tipoOcorrencia;
	private Integer isFoto;
	private Integer flagCritica;
	private String tipoLeitura;
	
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataReferencia;
}
