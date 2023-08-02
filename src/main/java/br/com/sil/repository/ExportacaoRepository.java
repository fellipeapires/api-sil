package br.com.sil.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sil.model.Exportacao;
import br.com.sil.repository.exportacao.ExportacaoRepositoryQuery;
import br.com.sil.repository.projection.ExportacaoProjection;


public interface ExportacaoRepository extends JpaRepository<Exportacao, Long>, ExportacaoRepositoryQuery {
	
@Transactional
@Query(value = "SELECT TOP 1 CONCAT('00', REPLACE(CONVERT(CHAR(10), B.DT_PREVISAO_INICIO, 103), '/', ''), REPLACE(CONVERT(CHAR(10), B.DT_PREVISAO_FIM, 103), '/', '')) AS CABECARIO_ARQUIVO "
		+ "FROM MED_LEITURA AS A "
		+ "INNER JOIN MED_IMPORTACAO AS B ON B.ID_IMPORTACAO = A.ID_IMPORTACAO "
		+ "INNER JOIN MED_GRUPO_FATURAMENTO AS C ON C.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
		+ "INNER JOIN MED_RETORNO_LEITURA AS D ON D.ID_LEITURA = A.ID_LEITURA "
		+ "WHERE B.ID_REGIONAL = ?1 AND B.DT_ANO_MES_REF = ?2 AND C.CD_GRUPO_FATURAMENTO = ?3 AND A.ST_TIPOLEITURA = ?4 "
		+ "GROUP BY CONCAT('00', REPLACE(CONVERT(CHAR(10), B.DT_PREVISAO_INICIO, 103), '/', ''), REPLACE(CONVERT(CHAR(10), B.DT_PREVISAO_FIM, 103), '/', '')) ", nativeQuery = true)
public String getCabecarioArquivo(Long idRegional, LocalDate dataReferencia, int grupoFaturamento, String tipoLeitura);

@Transactional
@Query(value = "SELECT "
		+ "			SUM(A.QT_REGISTRO) "
		+ "		FROM "
		+ "			MED_IMPORTACAO AS A "
		+ "			INNER JOIN MED_GRUPO_FATURAMENTO AS B ON B.ID_GRUPO_FATURAMENTO = A.ID_GRUPO_FATURAMENTO "
		+ "		WHERE "
		+ "		 	A.ID_REGIONAL = ?1 "
		+ "			AND A.DT_ANO_MES_REF = ?2 "
		+ "			AND B.CD_GRUPO_FATURAMENTO = ?3 "
		+ "			AND LEFT(A.NM_ARQUIVO, 4) = (CASE WHEN A.ID_REGIONAL = 1 AND 'N' = ?4 THEN 'LRMS' "
		+ "		WHEN A.ID_REGIONAL = 1 AND 'S' = ?4 THEN 'L12R' WHEN A.ID_REGIONAL = 1 AND 'R' = ?4 THEN 'RRMS' "
		+ "		WHEN A.ID_REGIONAL = 2 AND 'N' = ?4 THEN 'LV. ' WHEN A.ID_REGIONAL = 2 AND 'S' = ?4 THEN 'L12V' "
		+ "		WHEN A.ID_REGIONAL = 2 AND 'R' = ?4 THEN 'RV. ' ELSE ' ' END)", nativeQuery = true)
public int getQtdImportado(Long idRegional, LocalDate dataReferencia, int grupoFaturamento, String tipoLeitura);

@Transactional
@Query(value = "SELECT "
		+ "			COUNT(*) "
		+ "		FROM "
		+ "			MED_LEITURA AS A"
		+ "			INNER JOIN MED_IMPORTACAO AS B ON B.ID_IMPORTACAO = A.ID_IMPORTACAO "
		+ "			INNER JOIN MED_GRUPO_FATURAMENTO AS C ON C.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
		+ "			INNER JOIN MED_RETORNO_LEITURA D ON D.ID_LEITURA = A.ID_LEITURA "
		+ "		WHERE "
		+ "		 	B.ID_REGIONAL = ?1 "
		+ "			AND B.DT_ANO_MES_REF = ?2 "
		+ "			AND C.CD_GRUPO_FATURAMENTO = ?3 "
		+ "			AND D.FL_EXPORTADO = 1 AND D.FL_ATIVO = 1 "
		+ "			AND A.ST_TIPOLEITURA = ?4 ", nativeQuery = true)
public int getQtdExportado(Long idRegional, LocalDate dataReferencia, int grupoFaturamento, String tipoLeitura);

@Transactional
@Query(value = "SELECT "
		+ "			COUNT(*) "
		+ "		FROM "
		+ "			MED_LEITURA AS A"
		+ "			INNER JOIN MED_IMPORTACAO AS B ON B.ID_IMPORTACAO = A.ID_IMPORTACAO "
		+ "			INNER JOIN MED_GRUPO_FATURAMENTO AS C ON C.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
		+ "			INNER JOIN MED_RETORNO_LEITURA D ON D.ID_LEITURA = A.ID_LEITURA "
		+ "		WHERE "
		+ "		 	B.ID_REGIONAL = ?1 "
		+ "			AND B.DT_ANO_MES_REF = ?2 "
		+ "			AND C.CD_GRUPO_FATURAMENTO = ?3 "
		+ "			AND D.FL_EXPORTADO = 0 AND D.FL_ATIVO = 1", nativeQuery = true)
public int getQtdNaoExportado(Long idRegional, LocalDate dataReferencia, int grupoFaturamento);

@Transactional
@Query(value="SELECT "
		+ "	 MAX(C.NM_NOME)								AS REGIONAL "
		+ "	,MAX(B.DT_ANO_MES_REF)						AS DATAREFERENCIA "
		+ "	,MAX(D.CD_GRUPO_FATURAMENTO)				AS GRUPOFATURAMENTO "
		+ "	,MAX(B.NM_ARQUIVO)							AS ARQUIVO "
		+ "	,MAX(B.DT_EXPORTACAO)						AS DATAEXPORTACAO# "
		+ "	,MAX(B.DT_EXPORTACAO)						AS DATAEXPORTACAO "
		+ "	,MAX(B.QT_REGISTRO)							AS QTDARQUIVO "
		+ "	,MAX(B.QT_IMPORTADO)						AS QTDIMPORTADO "
		+ "	,MAX(B.QT_EXPORTADO)						AS QTDEXPORTADO "
		+ "	,MAX(B.QT_NAO_EXPORTADO)					AS QTDNAOEXPORTADO "
		+ "	,MAX(B.DS_EXPORTACAO)						AS OBSERVACAO "
		+ "FROM "
		+ "	MED_EXPORTACAO AS B "
		+ "	INNER JOIN CAD_REGIONAL AS C ON C.ID_REGIONAL = B.ID_REGIONAL "
		+ "	INNER JOIN MED_GRUPO_FATURAMENTO AS D ON D.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
		+ "WHERE "
		+ "	B.ID_REGIONAL = ?1 "
		+ "	AND B.DT_ANO_MES_REF = ?2 "
		+ "	AND D.CD_GRUPO_FATURAMENTO = ISNULL(?3, D.CD_GRUPO_FATURAMENTO) "
		+ "GROUP BY "
		+ "	D.CD_GRUPO_FATURAMENTO	"
		+ "	,B.ID_GRUPO_FATURAMENTO "
		+ "ORDER BY "
		+ "	DATAEXPORTACAO# DESC", nativeQuery = true)
public List<ExportacaoProjection> listar(Long idRegional, LocalDate dataReferencia, Integer grupoFaturamento);

/*
@Transactional
@Query(value="SELECT "
		+ "			MAX(C.NM_NOME)								AS REGIONAL "
		+ "			,MAX(B.DT_ANO_MES_REF)						AS DATAREFERENCIA "
		+ "			,MAX(D.CD_GRUPO_FATURAMENTO)				AS GRUPOFATURAMENTO "
		+ "			,MAX(B.NM_ARQUIVO)							AS ARQUIVO "
		+ "			,MAX(B.DT_EXPORTACAO)						AS DATAEXPORTACAO# "
		+ "			,MAX(B.QT_REGISTRO)							AS QTDARQUIVO "
		+ "			,MAX(B.DS_EXPORTACAO)						AS OBSERVACAO "
		+ "			,("
		+ "				SELECT "
		+ "					SUM(A1.QT_REGISTRO) "
		+ "				FROM "
		+ "					MED_IMPORTACAO AS A1 "
		+ "				WHERE "
		+ "					A1.ID_REGIONAL = ?1 "
		+ "					AND A1.DT_ANO_MES_REF = ?2 "
		+ "					AND A1.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
		+ "			) AS QTDIMPORTADO "
		+ "			,( "
		+ "				SELECT "
		+ "					COUNT(*) "
		+ "				FROM "
		+ "					MED_LEITURA AS A2 "
		+ "					INNER JOIN MED_IMPORTACAO AS B2 ON B2.ID_IMPORTACAO = A2.ID_IMPORTACAO "
		+ "					INNER JOIN MED_RETORNO_LEITURA C2  ON C2.ID_LEITURA = A2.ID_LEITURA "
		+ "				WHERE "
		+ "					B2.ID_REGIONAL = ?1 "
		+ "					AND B2.DT_ANO_MES_REF = ?2 "
		+ "					AND B2.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
		+ "					AND C2.FL_EXPORTADO = 1 AND C2.FL_ATIVO = 1 "
		+ "			) AS QTDEXPORTADO "
		+ "			,("
		+ "				SELECT "
		+ "					COUNT(*) "
		+ "				FROM "
		+ "					MED_LEITURA AS A2 "
		+ "					INNER JOIN MED_IMPORTACAO AS B2 ON B2.ID_IMPORTACAO = A2.ID_IMPORTACAO "
		+ "					INNER JOIN MED_RETORNO_LEITURA C2  ON C2.ID_LEITURA = A2.ID_LEITURA "
		+ "				WHERE "
		+ "					B2.ID_REGIONAL = ?1"
		+ "					AND B2.DT_ANO_MES_REF = ?2 "
		+ "					AND B2.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
		+ "					AND C2.FL_EXPORTADO = 0 AND C2.FL_ATIVO = 1 "
		+ "			) AS QTDNAOEXPORTADO "
		+ "		FROM "
		+ "			MED_EXPORTACAO AS B "
		+ "			INNER JOIN CAD_REGIONAL AS C ON C.ID_REGIONAL = B.ID_REGIONAL "
		+ "			INNER JOIN MED_GRUPO_FATURAMENTO AS D ON D.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
		+ "		WHERE "
		+ "			B.ID_REGIONAL = ?1 "
		+ "			AND B.DT_ANO_MES_REF = ?2 "
		+ "			AND D.CD_GRUPO_FATURAMENTO = ISNULL(?3, D.CD_GRUPO_FATURAMENTO) "
		+ "		GROUP BY "
		+ "			D.CD_GRUPO_FATURAMENTO	"
		+ "			,B.ID_GRUPO_FATURAMENTO "
		+ "		ORDER BY "
		+ "			DATAEXPORTACAO# DESC", nativeQuery = true)
public List<ExportacaoProjection> listar(Long idRegional, LocalDate dataReferencia, Integer grupoFaturamento);
*/

}
