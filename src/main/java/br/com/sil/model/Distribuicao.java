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

import com.sun.istack.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "MED_DISTRIBUICAO_LEITURA_REGISTRO")
public class Distribuicao implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_DISTRIBUICAO_LEITURA_REGISTRO")
	private Long id;
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name = "ID_REGIONAL")
	private Regional regional;
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name = "ID_LEITURA")
	private Leitura leitura;
	
	@NotNull
	@Column(name = "DT_DISTRIBUICAO")
	private LocalDateTime dataDistribuicao;
	
	@NotNull
	@Column(name = "FL_ASSOCIADO")
	private int associado;

}
