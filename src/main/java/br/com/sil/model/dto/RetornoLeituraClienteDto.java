package br.com.sil.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sil.repository.projection.RetornoFotoProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class RetornoLeituraClienteDto {
	
	private Long idLeitura;
	private String instalacao;
	private String medidor;
	private Integer leituraMedida;
	private String segmento;
	private String endereco;
	private String complemento;
	private String municipio;
	private Integer isFoto;
	private String latitude;
	private String longitude;

	private List<RetornoFotoProjection> listaFoto;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataLeitura;

}
