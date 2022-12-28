package br.com.sil.repository.projection;

import java.time.LocalDateTime;

public interface RetornoLeituraExportacaoProjection {
	Long getIdRetornoLeitura();
	LocalDateTime getDataLeitura();
	String getHoraLeitura();
	String getNumeroLeiturista();
	String getOrdemLeitura();
	String getUnidadeLeitura();
	String getCliente();
	String getEndereco();
	String getComplemento();
	String getMunicipio();
	String getCep();
	String getInstalacao();
	String getCodigoLogradouro();
	String getMedidor();
	String getTipoMedidor();
	String getSequencia();
	String getUnidadeMedida();
	String getMensAvisoMobile();
	String getSeguimento();
	String getRamoAtividade();
	Integer getUltimaLeitura();
	Integer getMediaTresMeses();
	Integer getLeituraMedida();
	Integer getCodigoOcorrencia();
	String getLeituraRepasse();
	String getTarefaLeitura();
	String getTarefaEntrega();
	Integer getOrdenacaoLeitura();
	String getMatriculaColaborador();
	String getLatitude();
	String getLongitude();
	Long getIdLeitura();
	Long getIdColaborador();
	String getObservacao();
}
