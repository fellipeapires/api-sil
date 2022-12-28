package br.com.sil.model;

import java.io.Serializable;
import java.time.LocalDate;

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
@Table(name = "MED_LEITURA")
public class Leitura implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LEITURA")
	private Long id;

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "ID_IMPORTACAO")
	private Importacao importacao;

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "ID_GRUPO_FATURAMENTO")
	private GrupoFaturamento grupoFaturamento;

	@Column(name = "DT_LEITURA")
	private LocalDate dataLeitura;

	@Column(name = "HORA_LEITURA")
	private String horaLeitura;

	@Column(name = "NR_LEITURISTA")
	private String numeroLeiturista;

	@Column(name = "NR_ORDEM_LEITURA")
	private String ordemLeitura;

	@Column(name = "NR_UNIDADE_LEITURA")
	private String unidadeLeitura;

	@Column(name = "NM_CLIENTE")
	private String cliente;
	
	@Column(name = "NM_ENDERECO")
	private String endereco;
	
	@Column(name = "NM_COMPLEMENTO")
	private String complemento;
	
	@Column(name = "NM_MUNICIPIO")
	private String municipio;
	
	@Column(name = "CD_CEP")
	private String cep;
	
	@Column(name = "CD_LOGRADOURO")
	private String codigoLogradouro;
	
	@Column(name = "NR_INSTALACAO")
	private String instalacao;
	
	@Column(name = "NR_MEDIDOR")
	private String medidor;
	
	@Column(name = "CD_TIPO_MEDIDOR")
	private String tipoMedidor;
	
	@Column(name = "CD_SEQUENCIA")
	private String sequencia;
	
	@Column(name = "CD_UNIDADE_MEDIDA")
	private String unidadeMedida;
	
	@Column(name = "DS_MENS_AVISO_MOBILE")
	private String mensAvisoMobile;
	
	@Column(name = "CD_SEGUIMENTO")
	private String codigoSeguimento;
	
	@Column(name = "CD_RAMO_ATIVIDADE")
	private String ramoAtividade;
	
	@Column(name = "NR_ULTIMA_LEITURA")
	private int ultimaLeitura;
	
	@Column(name = "NR_MEDIA3_MESES")
	private int media3Meses;
	
	@Column(name = "NR_LEITURA_MEDIDA")
	private int leituraMedida;
	
	@Column(name = "FL_LEITURA_REPASSE")
	private String flagLeituraRepasse;
	
	@Column(name = "CD_TAREFA_LEITURA")
	private String tarefaLeitura;
	
	@Column(name = "CD_TAREFA_ENTREGA")
	private String tarefaEntrega;
	
	@Column(name = "CD_ORDENACAO_LEITURA")
	private int ordenacaoLeitura;
	
	@Column(name = "NR_MATRICULA_LEITURISTA")
	private String matriculaLeiturista;
	
	@Column(name = "ST_TIPOLEITURA")
	private String tipoLeitura;
	
	@Column(name = "CD_FAIXAMINIMA")
	private int faixaMinima;
	
	@Column(name = "CD_FAIXAMAXIMA")
	private int faixaMaxima;
	
	@Column(name = "CD_OCORRENCIA")
	private String codigoOcorrencia;
	
}
