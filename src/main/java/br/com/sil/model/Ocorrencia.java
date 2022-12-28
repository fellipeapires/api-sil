package br.com.sil.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "MED_OCORRENCIA")
public class Ocorrencia implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_OCORRENCIA")
	private Long id;
	
	@NotNull
	@Column(name = "CD_OCORRENCIA")
	private Integer codigo;
	
	@NotNull
	@Column(name = "NM_NOME")
	private String nome;
	
	@NotNull
	@Column(name = "CD_SITUACAO")
	private int situacao;
	
	@NotNull
	@Column(name = "FL_TIPO_OCORRENCIA")
	private Integer tipoOcorrencia;

}
