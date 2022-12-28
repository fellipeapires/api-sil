package br.com.sil.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
@Entity
@Table(name = "RETORNO_FOTO")
public class RetornoFoto implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RETORNO_FOTO")
	private Long id;
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name = "ID_LEITURA")
	private Leitura leitura;
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@NotNull
	@Column(name = "NM_NOME")
	private String nome;
	
	@NotNull
	@Column(name = "DS_PATH")
	private String path;
	
	@NotNull
	@Column(name = "DT_FOTO")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataFoto;
	
	@NotNull
	@Column(name = "CD_LATITUDE")
	private String latitude;
	
	@NotNull
	@Column(name = "CD_LONGITUDE")
	private String longitude;
	
	@NotNull
	@Column(name = "NR_INSTALACAO")
	private String instalacao;
	
	@NotNull
	@Column(name = "NR_MEDIDOR")
	private String medidor;
	
	@NotNull
	@Column(name = "DS_IMAGEM")
	private String imagem;
	
	@NotNull
	@Column(name = "DS_MARCA")
	private String marca;
	
	@NotNull
	@Column(name = "DS_MODELO")
	private String modelo;
	
}
