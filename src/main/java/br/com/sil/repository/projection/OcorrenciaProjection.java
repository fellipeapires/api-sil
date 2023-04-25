package br.com.sil.repository.projection;

public interface OcorrenciaProjection {
	Long getId();
	Long getIdUsuario();
	Integer getQtdeRegistros();
	Integer getPagina();
	Integer getSituacao();
	Integer getCodigoOcorrencia();
	String getOcorrencia();
	Integer getMedia();
	Integer getUsoMobile();
	Integer getQtde();
}
