package br.com.sil.repository.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AcompanhamentoProjection {
	
	Long getIdRegional();
	LocalDate getDataReferencia();
	Long getIdGrupoFaturamento();
	Integer getGrupoFaturamento();
	String getTarefa();
	Long getidUsuario();
	String getNomeUsuario();
	Integer getAtribuido();
	Integer getComercio();
	Integer getColetivo();
	Integer getResidencial();
	Integer getInterno();
	Integer getOutros();
	Integer getLidoComercio();
	Integer getLidoColetivo();
	Integer getLidoResidencial();
	Integer getLidoInterno();
	Integer getTotalLido();
	Integer getTotalPendente();
	Integer getTotalCodigo();
	String getPorcentOcorrencia();
	Integer getQtdFoto();
	LocalDateTime getDataInicio();
	LocalDateTime getDataFim();
}
