package br.com.sil.repository.projection;

import java.time.LocalDate;

public interface LeituraProjection {
	
	Long getIdLeitura();
	LocalDate getDataReferencia();
	Integer getGrupoFaturamento();
	String getTarefa();
	String getEndereco();
	String getComplemento();
	String getComplemento2();
	String getMedidor();
	String getInstalacao();
	Integer getQtd();
}
