package br.com.sil.repository.projection;

public interface UsuarioMobileProjection {
	Long getId();
	Long getIdUsuario();
	Integer getQtdeRegistros();
	Integer getPagina();
	String getRegional();
	String getNome();
	String getLogin();
	String getSenha();
	String getMatricula();
	String getTelefone();
	String getIdDevice();
	Integer getSituacao();
	Integer getBaseDados();
	Integer getCargo();

}
