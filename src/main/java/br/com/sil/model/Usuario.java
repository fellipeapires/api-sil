package br.com.sil.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "SEG_USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USUARIO")
	private Long id;

	@NotNull
	@Column(name = "NR_CPF")
	private String cpf;

	@NotNull
	@Column(name = "NM_NOME")
	private String nome;

	@NotNull
	@Column(name = "NM_LOGIN")
	private String login;

	@NotNull
	@Column(name = "NM_SENHA")
	private String senha;

	@NotNull
	@Column(name = "DS_EMAIL")
	private String email;

	@NotNull
	@Column(name = "NR_MATRICULA")
	private String matricula;

	@Column(name = "ST_ALTERA_SENHA")
	private int alteraSenha;

	@NotNull
	@Column(name = "CD_SITUACAO")
	private int situacao;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USUARIO_PERMISSAO", joinColumns = @JoinColumn(name = "ID_USUARIO"), inverseJoinColumns = @JoinColumn(name = "ID_PERMISSAO"))
	private List<Permissao> permissoes;

	@NotNull
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SEG_USUARIO_REGIONAL", joinColumns = @JoinColumn(name = "ID_USUARIO"), inverseJoinColumns = @JoinColumn(name = "ID_REGIONAL"))
	private List<Regional> regionais;

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "ID_PERFIL_ACESSO")
	private PerfilAcesso perfilAcesso;
}
