package br.com.sil.repository.projection;

public interface DistribuicaoProjection {
	Long getIdLeitura();
	Long getIdRegional();
	Integer getGrupoFaturamento();
	String getDataReferencia();
	String getTarefa();
	String getEndereco();
	String getLocalidade();
	Integer getQtd();
}
