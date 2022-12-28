package br.com.sil.repository.filter;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class RetornoFotoFilter {
	private Long id;
	private Long idLeitura;
	private Long idUsuario;
	private String instalacao;
	private String medidor;
	private String path;
	
	/*HH:mm:ss*/
	
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dataInicio;
	
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dataFim;
}
