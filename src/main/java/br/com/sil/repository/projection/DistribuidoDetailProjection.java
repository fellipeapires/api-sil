package br.com.sil.repository.projection;

public interface DistribuidoDetailProjection {
	Long getIdDistribuicao();
	String getInstalacao();
	String getMedidor();
	String getEndereco();
	String getComplemento();
	String getMunicipio();
	Integer getStatus();
}
