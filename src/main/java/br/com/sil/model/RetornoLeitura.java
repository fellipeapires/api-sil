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
@Table(name = "MED_RETORNO_LEITURA")
public class RetornoLeitura implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RETORNO_LEITURA")
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
	@ManyToOne()
	@JoinColumn(name = "ID_OCORRENCIA")
	private Ocorrencia ocorrencia;
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name = "ID_REGIONAL")
	private Regional regional;
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name = "ID_GRUPO_FATURAMENTO")
	private GrupoFaturamento grupoFaturamento;
	
	@NotNull
	@Column(name="DT_ANO_MES_REF")
	private LocalDate dataReferencia;
	
	@NotNull
	@Column(name = "NR_LEITURA_ANTERIOR")
	private int leituraAnterior;
	
	@NotNull
	@Column(name = "NR_LEITURA_MEDIDA")
	private int leituraMedida;
	
	@NotNull
	@Column(name = "NR_VARIACAO_LEITURA")
	private Double variacaoLeitura;
	
	@NotNull
	@Column(name = "DT_LEITURA")
	private LocalDateTime dataLeitura;
	
	@NotNull
	@Column(name = "CD_TAREFA_LEITURA")
	private String tarefaLeitura;
	
	@NotNull
	@Column(name = "CD_TAREFA_ENTREGA")
	private String tarefaEntrega;
	
	@NotNull
	@Column(name = "CD_ORDENACAO_LEITURA")
	private int ordenacaoLeitura;
	
	@NotNull
	@Column(name = "CD_LATITUDE")
	private String latitude;
	
	@NotNull
	@Column(name = "CD_LONGITUDE")
	private String longitude;
	
	@NotNull
	@Column(name = "FL_CRITICA")
	private int flagCritica;
	
	@NotNull
	@Column(name = "DS_OBSERVACAO")
	private String observacao;
	
	@NotNull
	@Column(name = "FL_MEDIA")
	private int flagMedia;
	
	@NotNull
	@Column(name = "NR_INSTALACAO")
	private String instalacao;
	
	@NotNull
	@Column(name = "NR_MEDIDOR")
	private String medidor;
	
	@Column(name = "DT_ATUALIZACAO")
	private LocalDateTime dataAtualizacao;
	
	@NotNull
	@Column(name = "FL_FOTO")
	private Integer isFoto;
	
	@NotNull
	@Column(name = "FL_ATIVO")
	private int ativo;

}
