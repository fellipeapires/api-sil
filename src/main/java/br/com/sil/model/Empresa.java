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
@Table(name = "CAD_EMPRESA")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EMPRESA")
	private Long id;
	
	@NotNull
	@Column(name = "NR_CNPJ")
	private String cnpj;
	
	@NotNull
	@Column(name = "NM_NOME")
	private String nome;
	
	@NotNull
	@Column(name = "NM_FANTASIA")
	private String nomeFantasia;
	
	@NotNull
	@Column(name = "NM_CIDADE")
	private String cidade;
	
	@NotNull
	@Column(name = "CD_SITUACAO")
	private int situacao;

}
