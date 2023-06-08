package br.com.sil.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sil.model.RetornoFoto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class RetornoLeituraDto {
	private Long id;
	private Long idLeitura;
	private Long idLeituraRepasse;
	private Long idLeituraPasse;
	private Long idUsuario;
	private Long idUsuarioAlteracao;
	private int ocorrencia;
	private int leitura;
	private String observacao;
	private int qtdFoto;
	private List<RetornoFoto> listaFoto;
	private String versaoApp;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataLeitura;
}
