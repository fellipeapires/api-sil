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
@Table(name = "MED_EXPORTACAO")
public class Exportacao implements Serializable {

		private static final long serialVersionUID = 1L;

		@EqualsAndHashCode.Include
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "ID_EXPORTACAO")
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
		private int qtdExportacao;
		
		@NotNull
		@Column(name = "NM_ARQUIVO")
		private String nome;
		
		@NotNull
		@Column(name = "DT_EXPORTACAO")
		private LocalDateTime dataExportacao;
		
		@NotNull
		@Column(name = "DS_EXPORTACAO")
		private String observacao;
		
		@NotNull
		@Column(name = "DS_PATH")
		private String path;
		
		@Column(name = "QT_IMPORTADO")
		private Integer qtdImportado;
		
		@Column(name = "QT_EXPORTADO")
		private Integer qtdExportado;
		
		@Column(name = "QT_NAO_EXPORTADO")
		private Integer qtdNaoExportado;

}
