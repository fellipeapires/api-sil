package br.com.sil.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "MED_IMPORTACAO")
public class Importacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_IMPORTACAO")
	private Long id;
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name = "ID_REGIONAL")
	private Regional regional;
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name = "ID_GRUPO_FATURAMENTO")
	private GrupoFaturamento grupoFaturamento;
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@NotNull
	@Column(name="DT_ANO_MES_REF")
	private LocalDate dataReferencia;

	@NotNull
	@Column(name = "QT_REGISTRO")
	private int qtdImportacao;
	
	
	@Column(name="DT_PREVISAO_INICIO")
	private LocalDate dataInicio;
	
	
	@Column(name="DT_PREVISAO_FIM")
	private LocalDate dataFim;
	
	@NotNull
	@Column(name = "NM_ARQUIVO")
	private String nome;
	
	@NotNull
	@Column(name = "DT_IMPORTACAO")
	private LocalDateTime dataImportacao;
	
	@NotNull
	@Column(name = "DS_IMPORTACAO")
	private String observacao;
	
	@NotNull
	@Column(name = "DS_PATH")
	private String path;
}
