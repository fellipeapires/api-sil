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
@Table(name = "CAD_PERGUNTA_APR")
public class PerguntaApr implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PERGUNTA_APR")
	private Long id;
	
	@Column(name = "NR_PERGUNTA_APR")
	private int numeroPergunta;
	
	@Column(name = "DS_PERGUNTA_APR")
	private String pergunta;
	
	@NotNull
	@Column(name = "CD_SITUACAO")
	private int situacao;
}

