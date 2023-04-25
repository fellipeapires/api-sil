package br.com.sil.repository.projection;

public interface PerguntaAprProjection {
	Long getId();
	Long getIdUsuario();
	Integer getQtdeRegistros();
	Integer getPagina();
	Integer getNumeroPergunta();
	String getPergunta();	
	Integer getSituacao();
}
