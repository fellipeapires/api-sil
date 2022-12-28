package br.com.sil.model;

import java.io.Serializable;

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
@Table(name = "CAD_REGIONAL")
public class Regional implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_REGIONAL")
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name="ID_EMPRESA")
	private Empresa empresa;
	
	@NotNull
	@Column(name = "NM_NOME")
	private String nome;
	
	@NotNull
	@Column(name = "NM_CIDADE")
	private String cidade;
	
	@NotNull
	@Column(name = "CD_SITUACAO")
	private int situacao;
}
