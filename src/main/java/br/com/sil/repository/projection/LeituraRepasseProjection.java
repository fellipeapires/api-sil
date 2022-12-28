package br.com.sil.repository.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface LeituraRepasseProjection {
	Long getIdRegional();
	Long getIdRetornoLeitura();
	Long getIdLeituraPasse();
	Long getIdLeituraRepasse();
	Long getIdOcorrencia();
	Integer getIdGrupoFaturamentoPasse();
	LocalDate getDataReferencia();
	Integer getCodOcorrencia();
	String getOcorrencia();
	Long getIdUsuario();
	String getNomeUsuario();
	Integer getUltimaLeitura();
	Integer getLeituraMedida();
	Integer getMediaTresMeses();
	String getRamoAtividade();
	String getInstalacao();
	String getMedidor();
	LocalDateTime getDataLeitura();
	String getTarefa();
	String getObservacao();
	Integer getOrdenacaoLeitura();
	String getLatitude();
	String getLongitude();
	String getEndereco();
	String getMunicipio();
	String getComplemento();
}
