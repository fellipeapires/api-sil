package br.com.sil.repository.projection;

import java.time.LocalDateTime;

public interface RetornoLeituraClienteProjection {
	Long getIdLeitura();
	String getInstalacao();
	String getMedidor();
	Integer getLeituraMedida();
	LocalDateTime getDataLeitura();
	String getSegmento();
	String getEndereco();
	String getComplemento();
	String getMunicipio();
	Integer getIsFoto();
	String getLatitude();
	String getLongitude();
}
