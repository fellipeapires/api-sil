package br.com.sil.repository.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ExportacaoProjection {		
	String getRegional();
	LocalDate getDataReferencia();
	Integer getGrupoFaturamento();
	String getArquivo();
	LocalDateTime getDataExportacao();
	Integer getQtdArquivo();
	Integer getQtdImportado();
	Integer getQtdExportado();
	Integer getQtdNaoExportado();
	String getObservacao();
}
