package br.com.sil.repository.projection;

import java.time.LocalDateTime;

public interface MapaProjection {
	String getTarefa();
	String getInstalacao();
	String getMedidor();
	String getNomeUsuario();
	Integer getCodOcorrencia();
	Integer getLeitura();
	String getLatitude();
	String getLongitude();
	LocalDateTime getDataLeitura();
}
