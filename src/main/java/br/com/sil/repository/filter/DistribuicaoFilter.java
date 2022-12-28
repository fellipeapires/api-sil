package br.com.sil.repository.filter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class DistribuicaoFilter {
	private long id;
	private Long idRegional;
	private Long idLeitura;
	private Long idUsuario;
	private int associado;
	private int situacao;
	private Integer grupoFaturamento;
	private Integer tipoDistribuicao;
	
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataReferencia;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataDistribuicao;
}
