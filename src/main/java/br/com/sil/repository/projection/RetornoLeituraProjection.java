package br.com.sil.repository.projection;

import java.time.LocalDate;

public interface RetornoLeituraProjection {
	LocalDate  getDataReferencia();
	String getTarefa();
	String getInstalacao();
	String getMedidor();
	String getNomeUsuario();
	Integer getCodOcorrencia();
	String getOcorrencia();
	Integer getLeituraAnterior();
	Integer getLeituraMedida();
	Integer getMediaTresMeses();
}
