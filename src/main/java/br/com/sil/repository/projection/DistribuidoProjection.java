package br.com.sil.repository.projection;

public interface DistribuidoProjection {
	Long getIdRegional();
	Integer getGrupoFaturamento();
	String getDataReferencia();
	Long getIdUsuario();
	String getNomeUsuario();
	String getTarefa();
	String getStatus();
	Integer getQtd();
}
