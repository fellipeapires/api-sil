package br.com.sil.model.dto;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class RetornoLeituraMobileDto {
	private Long idFuncionario;
	private Long idLeitura;
	private int ocorrencia;
	private int leituraMedida;
	private String observacao;
	private String instalacao;
	private String medidor;
	private int leituraAnterior;
	private Long idDistribuicaoRegistro;
	private int tarefaLeitura;
	private int ordenacaoLeitura;
	private int tarefaEntrega;
	private String latitude;
	private String longitude;
	private int critica;
	private int media;
	private int enviado;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataLeitura;

}
