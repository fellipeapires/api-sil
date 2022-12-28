package br.com.sil.repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sil.model.Exportacao;
import br.com.sil.repository.exportacao.ExportacaoRepositoryQuery;


public interface ExportacaoRepository extends JpaRepository<Exportacao, Long>, ExportacaoRepositoryQuery {
	
	
	
	 
@Transactional
@Query(value = "SELECT CONCAT('00', REPLACE(CONVERT(CHAR(10), B.DT_PREVISAO_INICIO, 103), '/', ''), REPLACE(CONVERT(CHAR(10), B.DT_PREVISAO_FIM, 103), '/', '')) AS CABECARIO_ARQUIVO "
		+ "FROM MED_LEITURA AS A "
		+ "INNER JOIN MED_IMPORTACAO AS B ON B.ID_IMPORTACAO = A.ID_IMPORTACAO "
		+ "INNER JOIN MED_GRUPO_FATURAMENTO AS C ON C.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
		+ "INNER JOIN MED_RETORNO_LEITURA AS D ON D.ID_LEITURA = A.ID_LEITURA "
		+ "WHERE B.ID_REGIONAL = ?1 AND B.DT_ANO_MES_REF = ?2 AND C.CD_GRUPO_FATURAMENTO = ?3 AND A.ST_TIPOLEITURA = ?4 "
		+ "GROUP BY CONCAT('00', REPLACE(CONVERT(CHAR(10), B.DT_PREVISAO_INICIO, 103), '/', ''), REPLACE(CONVERT(CHAR(10), B.DT_PREVISAO_FIM, 103), '/', '')) ", nativeQuery = true)
public String getCabecarioArquivo(Long idRegional, LocalDate dataReferencia, int grupoFaturamento, String tipoLeitura);


}
