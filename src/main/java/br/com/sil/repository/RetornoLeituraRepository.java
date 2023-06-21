package br.com.sil.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.sil.model.RetornoLeitura;
import br.com.sil.repository.projection.AcompanhamentoDetailProjection;
import br.com.sil.repository.projection.AcompanhamentoProjection;
import br.com.sil.repository.projection.AcompanhamentoTotalProjection;
import br.com.sil.repository.projection.MapaProjection;
import br.com.sil.repository.projection.RetornoLeituraClienteProjection;
import br.com.sil.repository.projection.RetornoLeituraExportacaoProjection;
import br.com.sil.repository.projection.RetornoLeituraProjection;
import br.com.sil.repository.retornoleitura.RetornoLeituraRepositoryQuery;


public interface RetornoLeituraRepository extends JpaRepository<RetornoLeitura, Long>, RetornoLeituraRepositoryQuery {
	
	@Transactional
	@Query(value = "SELECT COUNT(*) AS QTD FROM MED_RETORNO_LEITURA WHERE ID_LEITURA = ?1 AND FL_ATIVO = 1 ", nativeQuery = true)
	public int isExiste(Long idLeitura);
	
	@Transactional
	@Query(value = "SELECT COUNT(*) AS QTD FROM MED_RETORNO_LEITURA WHERE ID_LEITURA = ?1 AND FL_ATIVO = 1 AND FL_CRITICA = 1 ", nativeQuery = true)
	public int isRevisado(Long idLeitura);

	@Transactional
	@Query(value = "SELECT B.ID_REGIONAL AS IDREGIONAL, B.DT_ANO_MES_REF AS DATAREFERENCIA, "
			+ "B.ID_GRUPO_FATURAMENTO AS IDGRUPOFATURAMENTO, C.CD_GRUPO_FATURAMENTO AS GRUPOFATURAMENTO,"
			+ "A.CD_TAREFA_LEITURA AS TAREFA, D.ID_USUARIO AS IDUSUARIO, E.NM_NOME AS NOMEUSUARIO, "
			+ "SUM(CASE WHEN D.ID_LEITURA IS NOT NULL THEN 1 ELSE 0 END) AS ATRIBUIDO, "
			+ "SUM(CASE WHEN A.CD_SEGUIMENTO = '02' THEN 1 ELSE 0 END) AS COMERCIO, "
			+ "SUM(CASE WHEN A.CD_SEGUIMENTO = '13' THEN 1 ELSE 0 END) AS COLETIVO, "
			+ "SUM(CASE WHEN A.CD_SEGUIMENTO = '01' THEN 1 ELSE 0 END) AS RESIDENCIAL, "
			+ "SUM(CASE WHEN LEFT(A.DS_MENS_AVISO_MOBILE, 2)='MI' THEN 1 ELSE 0 END) AS INTERNO, "
			+ "SUM(CASE WHEN (A.CD_SEGUIMENTO IN ('03', '07', '18', '19', ' ')) THEN 1 ELSE 0 END) AS OUTROS, "
			+ "SUM(CASE WHEN A.CD_SEGUIMENTO = '02' AND F.ID_LEITURA IS NOT NULL THEN 1 ELSE 0 END) LIDOCOMERCIO, "
			+ "SUM(CASE WHEN A.CD_SEGUIMENTO = '13' AND F.ID_LEITURA IS NOT NULL THEN 1 ELSE 0 END) AS LIDOCOLETIVO, "
			+ "SUM(CASE WHEN A.CD_SEGUIMENTO = '01' AND F.ID_LEITURA IS NOT NULL THEN 1 ELSE 0 END) AS LIDORESIDENCIAL, "
			+ "SUM(CASE WHEN LEFT(A.DS_MENS_AVISO_MOBILE, 2)='MI' AND F.ID_LEITURA IS NOT NULL THEN 1 ELSE 0 END) AS LIDOINTERNO, "
			+ "SUM(CASE WHEN F.ID_RETORNO_LEITURA IS NOT NULL THEN 1 ELSE 0 END) AS TOTALLIDO, "
			+ "SUM(CASE WHEN F.ID_LEITURA IS NULL THEN 1 ELSE 0 END) AS TOTALPENDENTE, "
			+ "SUM(CASE WHEN G.FL_TIPO_OCORRENCIA = 1 THEN 1 ELSE 0 END) AS TOTALCODIGO, "
			+ "CAST(ROUND((100.*SUM(CASE WHEN F.ID_RETORNO_LEITURA IS NOT NULL AND G.FL_TIPO_OCORRENCIA = 1 THEN 1 ELSE 0 END))/"
			+ "CASE WHEN SUM(CASE WHEN F.ID_RETORNO_LEITURA IS NOT NULL THEN 1 ELSE 0 END) > 0 THEN "
			+ "SUM(CASE WHEN F.ID_RETORNO_LEITURA IS NOT NULL THEN 1 ELSE 0 END) ELSE 1 END, 8) AS DECIMAL(10,2)) AS PORCENTOCORRENCIA, "
			+ "(SELECT MIN(F2.DT_LEITURA) "
			+ "FROM MED_LEITURA AS A2 "
			+ "INNER JOIN MED_IMPORTACAO AS B2 ON B2.ID_IMPORTACAO = A2.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS C2 ON C2.ID_GRUPO_FATURAMENTO = B2.ID_GRUPO_FATURAMENTO "
			+ "INNER JOIN SEG_USUARIO AS E2 ON E2.ID_USUARIO = D.ID_USUARIO "
			+ "INNER JOIN MED_RETORNO_LEITURA AS F2 ON F2.ID_LEITURA = A2.ID_LEITURA AND F2.ID_REGIONAL = ?1 AND F2.ID_GRUPO_FATURAMENTO = B2.ID_GRUPO_FATURAMENTO AND F2.FL_ATIVO = 1 "
			+ "WHERE B2.ID_REGIONAL = ?1 AND B2.DT_ANO_MES_REF = ?2 AND C2.CD_GRUPO_FATURAMENTO = ?3 "
			+ "AND F2.ID_USUARIO = D.ID_USUARIO AND A2.CD_TAREFA_LEITURA = A.CD_TAREFA_LEITURA) "
			+ "AS DATAINICIO, "
			+ "(SELECT MAX(F3.DT_LEITURA) "
			+ "FROM MED_LEITURA AS A3 "
			+ "INNER JOIN MED_IMPORTACAO AS B3 ON B3.ID_IMPORTACAO = A3.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS C3 ON C3.ID_GRUPO_FATURAMENTO = B3.ID_GRUPO_FATURAMENTO "
			+ "INNER JOIN SEG_USUARIO AS E3 ON E3.ID_USUARIO = D.ID_USUARIO "
			+ "INNER JOIN MED_RETORNO_LEITURA AS F3 ON F3.ID_LEITURA = A3.ID_LEITURA AND F3.ID_REGIONAL = ?1 AND F3.ID_GRUPO_FATURAMENTO = B3.ID_GRUPO_FATURAMENTO AND F3.FL_ATIVO = 1 "
			+ "WHERE B3.ID_REGIONAL = ?1 AND B3.DT_ANO_MES_REF = ?2 AND C3.CD_GRUPO_FATURAMENTO = ?3 "
			+ "AND F3.ID_USUARIO = D.ID_USUARIO AND A3.CD_TAREFA_LEITURA = A.CD_TAREFA_LEITURA) "
			+ "AS DATAFIM, "
			+ "(SELECT DISTINCT COUNT(*) FROM RETORNO_FOTO AS RF "
			+ "INNER JOIN MED_RETORNO_LEITURA AS F4 ON F4.ID_LEITURA = RF.ID_LEITURA AND F4.ID_REGIONAL = ?1 AND F4.FL_ATIVO = 1 "
			+ "INNER JOIN MED_LEITURA AS A4 ON A4.ID_LEITURA = F4.ID_LEITURA "
			+ "INNER JOIN MED_IMPORTACAO AS B4 ON B4.ID_IMPORTACAO = A4.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS C4 ON C4.ID_GRUPO_FATURAMENTO = B4.ID_GRUPO_FATURAMENTO "
			+ "WHERE B4.ID_REGIONAL = ?1 AND B4.DT_ANO_MES_REF = ?2 "
			+ "AND C4.CD_GRUPO_FATURAMENTO = ?3 "
			+ "AND RF.ID_USUARIO = D.ID_USUARIO "
			+ "AND RF.NR_INSTALACAO !='0' AND RF.NR_INSTALACAO IS NOT NULL "
			+ "AND A4.CD_TAREFA_LEITURA = A.CD_TAREFA_LEITURA) "
			+ "AS QTDFOTO "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_IMPORTACAO AS B ON B.ID_IMPORTACAO = A.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS C ON C.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "INNER JOIN MED_DISTRIBUICAO_LEITURA_REGISTRO AS D ON D.ID_LEITURA = A.ID_LEITURA AND D.DT_ANO_MES_REF = ?2 AND D.ID_REGIONAL = ?1 AND D.CD_GRUPO_FATURAMENTO = ?3 "
			+ "INNER JOIN SEG_USUARIO AS E ON E.ID_USUARIO = D.ID_USUARIO "
			+ "LEFT OUTER JOIN MED_RETORNO_LEITURA AS F ON F.ID_LEITURA = A.ID_LEITURA AND F.ID_REGIONAL = ?1 AND F.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO AND F.FL_ATIVO = 1 "
			+ "LEFT OUTER JOIN MED_OCORRENCIA AS G ON G.ID_OCORRENCIA = F.ID_OCORRENCIA AND F.FL_ATIVO = 1 "
			+ "WHERE B.ID_REGIONAL = ?1 AND B.DT_ANO_MES_REF = ?2 AND C.CD_GRUPO_FATURAMENTO = ?3 "
			+ "AND A.CD_SEGUIMENTO = ISNULL(?4, A.CD_SEGUIMENTO) AND E.ID_USUARIO = ISNULL(?5, E.ID_USUARIO) "
			+ "AND LEFT(A.DS_MENS_AVISO_MOBILE, 2) = ISNULL(?6, LEFT(A.DS_MENS_AVISO_MOBILE, 2)) "
			+ "GROUP BY A.FL_LEITURA_REPASSE, B.ID_REGIONAL, B.ID_GRUPO_FATURAMENTO, "
			+ "C.CD_GRUPO_FATURAMENTO, D.ID_USUARIO, E.NM_NOME, B.DT_ANO_MES_REF, A.CD_TAREFA_LEITURA "
			+ "ORDER BY A.CD_TAREFA_LEITURA, D.ID_USUARIO ASC ", nativeQuery = true)
	public List<AcompanhamentoProjection> getAcompanhamento(Long idRegional, LocalDate dataReferencia, int grupoFaturamento, String segmento, Long idUsuario, String msgMobile);
	
	@Transactional
	@Query(value = "SELECT "
			+ "("
			+ "SELECT COUNT(*) "
			+ "FROM MED_LEITURA AS A2 "
			+ "INNER JOIN MED_IMPORTACAO AS B2 ON B2.ID_IMPORTACAO = A2.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS G2 ON G2.ID_GRUPO_FATURAMENTO = B2.ID_GRUPO_FATURAMENTO "
			+ "WHERE B2.ID_REGIONAL = ?1 AND B2.DT_ANO_MES_REF = ?2 AND G2.CD_GRUPO_FATURAMENTO = ?3 "
			+ ") AS LOTE, "
			+ "SUM(CASE WHEN C.ID_LEITURA IS NULL THEN 1 ELSE 0 END) AS NAOATRIBUIDO, "
			+ "("
			+ "SELECT TOP 1 ROW_NUMBER() OVER(ORDER BY A3.ID_LEITURA ASC) AS QTD# "
			+ "FROM MED_LEITURA AS A3 "
			+ "INNER JOIN MED_IMPORTACAO AS B3 ON B3.ID_IMPORTACAO = A3.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS G3 ON G3.ID_GRUPO_FATURAMENTO = B3.ID_GRUPO_FATURAMENTO "
			+ "INNER JOIN MED_DISTRIBUICAO_LEITURA_REGISTRO AS C3 ON C3.ID_LEITURA = A3.ID_LEITURA AND C3.DT_ANO_MES_REF = ?2 AND C3.ID_REGIONAL = ?1 AND C3.CD_GRUPO_FATURAMENTO = ?3 "
			+ "WHERE B3.ID_REGIONAL = ?1 AND B3.DT_ANO_MES_REF = ?2 AND G3.CD_GRUPO_FATURAMENTO = ?3 GROUP BY A3.ID_LEITURA ORDER BY QTD# DESC "
			+ ") AS ATRIBUIDO, "
			+ "("
			+ "SELECT COUNT(*) "
			+ "FROM MED_LEITURA AS A4 "
			+ "INNER JOIN MED_IMPORTACAO AS B4 ON B4.ID_IMPORTACAO = A4.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS G4 ON G4.ID_GRUPO_FATURAMENTO = B4.ID_GRUPO_FATURAMENTO "
			+ "INNER JOIN MED_RETORNO_LEITURA AS D4 ON D4.ID_LEITURA = A4.ID_LEITURA AND D4.ID_REGIONAL = ?1 AND D4.ID_GRUPO_FATURAMENTO = B4.ID_GRUPO_FATURAMENTO "
			+ "WHERE B4.ID_REGIONAL = ?1 AND B4.DT_ANO_MES_REF = ?2 AND G4.CD_GRUPO_FATURAMENTO = ?3 AND D4.FL_ATIVO = 1 "
			+ ") AS LIDO, "
			+ "("
			+ "SELECT COUNT(*) "
			+ "FROM MED_LEITURA AS A5 "
			+ "INNER JOIN MED_IMPORTACAO AS B5 ON B5.ID_IMPORTACAO = A5.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS G5 ON G5.ID_GRUPO_FATURAMENTO = B5.ID_GRUPO_FATURAMENTO "
			+ "LEFT OUTER JOIN MED_RETORNO_LEITURA AS D5 ON D5.ID_LEITURA = A5.ID_LEITURA AND D5.ID_REGIONAL = ?1 AND D5.ID_GRUPO_FATURAMENTO = B5.ID_GRUPO_FATURAMENTO AND D5.FL_ATIVO = 1 "
			+ "WHERE B5.ID_REGIONAL = ?1 AND B5.DT_ANO_MES_REF = ?2 AND G5.CD_GRUPO_FATURAMENTO = ?3 AND D5.ID_LEITURA IS NULL "
			+ ") AS PENDENTE "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_IMPORTACAO AS B ON B.ID_IMPORTACAO = A.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS G ON G.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "LEFT OUTER JOIN MED_DISTRIBUICAO_LEITURA_REGISTRO AS C ON C.ID_LEITURA = A.ID_LEITURA AND C.DT_ANO_MES_REF = ?2 AND C.ID_REGIONAL = ?1 AND C.CD_GRUPO_FATURAMENTO = ?3 "
			+ "WHERE B.ID_REGIONAL = ?1 AND B.DT_ANO_MES_REF = ?2 AND G.CD_GRUPO_FATURAMENTO = ?3 ", nativeQuery = true)
	public AcompanhamentoTotalProjection getAcompanhamentoTotal(Long idRegional, LocalDate dataReferencia, int grupoFaturamento);
	
	@Transactional
	@Query(value = "SELECT A.NM_CLIENTE AS CLIENTE, A.NR_INSTALACAO AS INSTALACAO, A.NR_MEDIDOR AS MEDIDOR, "
			+ "CASE WHEN A.CD_SEGUIMENTO = '01' THEN 'RESIDENCIAL' WHEN A.CD_SEGUIMENTO = '02' "
			+ "THEN 'COMERCIO' WHEN A.CD_SEGUIMENTO = '13' THEN 'COLETIVO' "
			+ "WHEN A.CD_SEGUIMENTO = '19' THEN 'REFRIGERACAO' ELSE 'OUTROS' END AS SEGMENTO, "
			+ "CONCAT(RTRIM(LTRIM(LEFT(A.NM_ENDERECO, 62))), ', ', RTRIM(LTRIM(SUBSTRING(A.NM_ENDERECO, 62, 10))), ' ', RTRIM(LTRIM(RIGHT(A.NM_ENDERECO, 10)))) AS ENDERECO, "
			+ "RTRIM(LTRIM(A.NM_MUNICIPIO)) AS LOCALIDADE, CASE WHEN E.ID_LEITURA IS NOT NULL THEN 1 ELSE 0 END AS STATUS "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_IMPORTACAO AS B ON A.ID_IMPORTACAO = B.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS D ON D.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "INNER JOIN MED_DISTRIBUICAO_LEITURA_REGISTRO AS C ON C.ID_LEITURA = A.ID_LEITURA AND C.DT_ANO_MES_REF = ?2 AND C.ID_REGIONAL = ?1 AND C.CD_GRUPO_FATURAMENTO = ?3 "
			+ "LEFT OUTER JOIN MED_RETORNO_LEITURA AS E ON E.ID_LEITURA = A.ID_LEITURA AND E.ID_REGIONAL = ?1 AND E.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO AND E.FL_ATIVO = 1 "
			+ "WHERE B.ID_REGIONAL = ?1 AND B.DT_ANO_MES_REF = ?2 AND D.CD_GRUPO_FATURAMENTO = ?3 "
			+ "AND A.CD_TAREFA_LEITURA = ?4 AND C.ID_USUARIO = ?5 "
			+ "GROUP BY E.ID_LEITURA, A.NM_CLIENTE, A.NR_INSTALACAO, A.NR_MEDIDOR, A.CD_SEGUIMENTO, "
			+ "CONCAT(RTRIM(LTRIM(LEFT(A.NM_ENDERECO, 62))), ', ', RTRIM(LTRIM(SUBSTRING(A.NM_ENDERECO, 62, 10))), ' ', RTRIM(LTRIM(RIGHT(A.NM_ENDERECO, 10)))), A.NM_MUNICIPIO "
			+ "ORDER BY STATUS ASC", nativeQuery = true)
	public List<AcompanhamentoDetailProjection> getAcompanhamentoDetail(Long idRegional, LocalDate dataReferencia, int grupoFaturamento, String tarefa, Long idUsuario);
	
	@Transactional
	@Query(value = "SELECT D.CD_TAREFA_LEITURA AS TAREFA, D.NR_INSTALACAO AS INSTALACAO, D.NR_MEDIDOR AS MEDIDOR, G.NM_NOME AS NOMEUSUARIO, F.CD_OCORRENCIA AS CODOCORRENCIA, "
			+ "D.NR_LEITURA_MEDIDA AS LEITURA, D.CD_LATITUDE AS LATITUDE, D.CD_LONGITUDE AS LONGITUDE, D.DT_LEITURA AS DATALEITURA "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_IMPORTACAO  AS B ON B.ID_IMPORTACAO = A.ID_IMPORTACAO "
			+ "INNER JOIN MED_DISTRIBUICAO_LEITURA_REGISTRO AS C ON C.ID_LEITURA = A.ID_LEITURA AND C.DT_ANO_MES_REF = ?2 AND C.ID_REGIONAL = ?1 AND C.CD_GRUPO_FATURAMENTO = ?3 "
			+ "INNER JOIN MED_RETORNO_LEITURA AS D ON D.ID_LEITURA = A.ID_LEITURA AND D.ID_REGIONAL = ?1 AND D.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO AND D.FL_ATIVO = 1 "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS E ON E.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "INNER JOIN MED_OCORRENCIA AS F ON F.ID_OCORRENCIA = D.ID_OCORRENCIA "
			+ "INNER JOIN SEG_USUARIO AS G ON G.ID_USUARIO = D.ID_USUARIO "
			+ "WHERE B.ID_REGIONAL = ?1 AND B.DT_ANO_MES_REF = ?2 AND E.CD_GRUPO_FATURAMENTO = ?3 "
			+ "AND D.CD_TAREFA_LEITURA = ?4 AND D.ID_USUARIO = ?5 AND D.CD_LATITUDE != '0' AND D.CD_LONGITUDE != '0' AND D.FL_ATIVO = 1 "
			+ "ORDER BY D.DT_LEITURA ASC", nativeQuery = true)
	public List<MapaProjection> getMapa(Long idRegional, LocalDate dataReferencia, int grupoFaturamento, String tarefa, Long idUsuario);
	
	@Transactional
	@Query(value = "SELECT D.CD_TAREFA_LEITURA AS TAREFA, D.NR_INSTALACAO AS INSTALACAO, D.NR_MEDIDOR AS MEDIDOR, G.NM_NOME AS NOMEUSUARIO, F.CD_OCORRENCIA AS CODOCORRENCIA, "
			+ "D.NR_LEITURA_MEDIDA AS LEITURA, D.CD_LATITUDE AS LATITUDE, D.CD_LONGITUDE AS LONGITUDE, D.DT_LEITURA AS DATALEITURA "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_IMPORTACAO  AS B ON B.ID_IMPORTACAO = A.ID_IMPORTACAO "
			+ "INNER JOIN MED_RETORNO_LEITURA AS D ON D.ID_LEITURA = A.ID_LEITURA "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS E ON E.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "INNER JOIN MED_OCORRENCIA AS F ON F.ID_OCORRENCIA = D.ID_OCORRENCIA "
			+ "INNER JOIN SEG_USUARIO AS G ON G.ID_USUARIO = D.ID_USUARIO "
			+ "WHERE D.ID_RETORNO_LEITURA = ?1 AND D.CD_LATITUDE != '0' AND D.CD_LONGITUDE != '0' AND D.FL_ATIVO = 1 "
			+ "ORDER BY D.DT_LEITURA ASC", nativeQuery = true)
	public List<MapaProjection> getMapaPorRetornoLeitura(Long idRetornoLeitura);
	
	@Transactional
	@Query(value = "SELECT D.CD_TAREFA_LEITURA AS TAREFA, D.NR_INSTALACAO AS INSTALACAO, D.NR_MEDIDOR AS MEDIDOR, G.NM_NOME AS NOMEUSUARIO, F.CD_OCORRENCIA AS CODOCORRENCIA, "
			+ "D.NR_LEITURA_MEDIDA AS LEITURA, D.CD_LATITUDE AS LATITUDE, D.CD_LONGITUDE AS LONGITUDE, D.DT_LEITURA AS DATALEITURA "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_IMPORTACAO  AS B ON B.ID_IMPORTACAO = A.ID_IMPORTACAO "
			+ "INNER JOIN MED_RETORNO_LEITURA AS D ON D.ID_LEITURA = A.ID_LEITURA "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS E ON E.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "INNER JOIN MED_OCORRENCIA AS F ON F.ID_OCORRENCIA = D.ID_OCORRENCIA "
			+ "INNER JOIN SEG_USUARIO AS G ON G.ID_USUARIO = D.ID_USUARIO "
			+ "WHERE B.ID_REGIONAL = ?1 AND B.DT_ANO_MES_REF = ?2 AND E.CD_GRUPO_FATURAMENTO = ?3 "
			+ "AND D.CD_TAREFA_LEITURA = ISNULL((CASE WHEN ?4 = '0' THEN NULL ELSE ?4 END), D.CD_TAREFA_LEITURA) "
			+ "AND D.ID_USUARIO = ISNULL((CASE WHEN ?5 = 0 THEN NULL ELSE ?5 END), D.ID_USUARIO) "
			+ "AND D.CD_LATITUDE != '0' AND D.CD_LONGITUDE != '0' AND D.FL_ATIVO = 1 "
			+ "ORDER BY D.DT_LEITURA ASC", nativeQuery = true)
	public List<MapaProjection> getMapaRoteiro(Long idRegional, LocalDate dataReferencia, int grupoFaturamento, String tarefa, Long idUsuario);
	
	@Transactional
	@Query(value = "SELECT * FROM ("
			+ "SELECT D.DT_ANO_MES_REF AS DATAREFERENCIA, D.CD_TAREFA_LEITURA AS TAREFA, D.NR_INSTALACAO AS INSTALACAO, D.NR_MEDIDOR AS MEDIDOR, "
			+ "G.NM_NOME AS NOMEUSUARIO, F.CD_OCORRENCIA AS CODOCORRENCIA, F.NM_NOME AS OCORRENCIA, D.NR_LEITURA_ANTERIOR AS LEITURAANTERIOR, "
			+ "D.NR_LEITURA_MEDIDA AS LEITURAMEDIDA, A.NR_MEDIA3_MESES AS MEDIATRESMESES, "
			+ "CASE WHEN D.DT_ANO_MES_REF = DATEADD(MONTH, -2, ?1) THEN 'MESUM' "
			+ "WHEN D.DT_ANO_MES_REF = DATEADD(MONTH, -1, ?1) THEN 'MESDOIS' END AS ANOMES, "
			+ "@@ROWCOUNT AS QTD "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_IMPORTACAO AS B ON B.ID_IMPORTACAO = A.ID_IMPORTACAO "
			+ "INNER JOIN MED_RETORNO_LEITURA AS D ON D.ID_LEITURA = A.ID_LEITURA "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS E ON E.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "INNER JOIN MED_OCORRENCIA AS F ON F.ID_OCORRENCIA = D.ID_OCORRENCIA "
			+ "INNER JOIN SEG_USUARIO AS G ON G.ID_USUARIO = D.ID_USUARIO "
			+ "WHERE B.DT_ANO_MES_REF IN(DATEADD(MONTH, -1, ?1), DATEADD(MONTH, -2, ?1)) "
			+ "AND B.ID_REGIONAL = ?2 AND E.CD_GRUPO_FATURAMENTO = ?3 AND D.NR_INSTALACAO = ?4 AND D.NR_MEDIDOR = ?5 AND D.FL_ATIVO = 1 "
			+ ") LINHAS "
			+ "PIVOT (COUNT(QTD) FOR ANOMES IN([MESUM], [MESDOIS], [MESATUAL])) COLUNAS ", nativeQuery = true)
	public List<RetornoLeituraProjection> getRetornoLeituraDetail(LocalDate dataReferencia, Long idRegional, int grupoFaturamento, String instalacao, String medidor);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO LOG_RETORNO_LEITURA (ID_USUARIO, ID_RETORNO_LEITURA_ANTERIOR, ID_RETORNO_LEITURA_ATUAL, DS_ALTERACAO, DT_ATUALIZACAO) VALUES (?1, ?2, ?3, ?4, ?5);", nativeQuery = true)
	public int setLogLancamentoLeitura(Long idUsuario, Long idRetornoLeituraAnterior,  Long idRetornoLeituraAtual, String descricao, LocalDateTime dataAtualizacao);
	
	@Transactional
	@Query(value = "SELECT CONVERT(DATETIME, CONCAT(I.DT_PREVISAO_FIM, ' ', CONVERT(VARCHAR(8), B.DT_LEITURA, 114)), 20) AS DATALEITURA, "
			+ "B.ID_RETORNO_LEITURA AS IDRETORNOLEITURA, CONVERT(VARCHAR(8), B.DT_LEITURA,114) AS HORALEITURA, A.NR_LEITURISTA AS NUMEROLEITURISTA, A.NR_ORDEM_LEITURA AS ORDEMLEITURA, "
			+ "A.NR_UNIDADE_LEITURA AS UNIDADELEITURA, A.NM_CLIENTE AS CLIENTE, A.NM_ENDERECO AS ENDERECO, A.NM_COMPLEMENTO AS COMPLEMENTO, A.NM_MUNICIPIO AS MUNICIPIO, A.CD_CEP AS CEP, "
			+ "A.NR_INSTALACAO AS INSTALACAO, A.CD_LOGRADOURO AS CODIGOLOGRADOURO, A.NR_MEDIDOR AS MEDIDOR, A.CD_TIPO_MEDIDOR AS TIPOMEDIDOR, A.CD_SEQUENCIA AS SEQUENCIA, A.CD_UNIDADE_MEDIDA AS UNIDADEMEDIDA, "
			+ "A.DS_MENS_AVISO_MOBILE AS MENSAVISOMOBILE, A.CD_SEGUIMENTO AS SEGUIMENTO, A.CD_RAMO_ATIVIDADE AS RAMOATIVIDADE, A.NR_ULTIMA_LEITURA AS ULTIMALEITURA, A.NR_MEDIA3_MESES AS MEDIATRESMESES, "
			+ "CASE WHEN D.FL_TIPO_OCORRENCIA = 1 AND B.NR_LEITURA_MEDIDA > 0 THEN 0 ELSE B.NR_LEITURA_MEDIDA END AS LEITURAMEDIDA, "
			+ "D.CD_OCORRENCIA AS CODIGOOCORRENCIA, A.FL_LEITURA_REPASSE AS LEITURAREPASSE, A.CD_TAREFA_LEITURA AS TAREFALEITURA, B.CD_ORDENACAO_LEITURA AS ORDENACAOLEITURA, A.CD_TAREFA_ENTREGA AS TAREFAENTREGA, "
			+ "REPLICATE('0',8- LEN(C.NR_MATRICULA)) + RTRIM(C.NR_MATRICULA) AS MATRICULACOLABORADOR, "
			+ "CASE WHEN B.CD_LATITUDE != '0' THEN LEFT(B.CD_LATITUDE, 7) ELSE B.CD_LATITUDE END AS LATITUDE, "
			+ "CASE WHEN B.CD_LONGITUDE != '0' THEN LEFT(B.CD_LONGITUDE, 7) ELSE B.CD_LONGITUDE END AS LONGITUDE, "
			+ "B.ID_LEITURA AS IDLEITURA, B.ID_USUARIO AS IDCOLABORADOR, "
			+ "(CASE WHEN B.DS_OBSERVACAO IS NOT NULL THEN LEFT(B.DS_OBSERVACAO, 30) ELSE '' END) AS OBSERVACAO "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_RETORNO_LEITURA AS B ON A.ID_LEITURA = B.ID_LEITURA "
			+ "INNER JOIN MED_OCORRENCIA AS D ON B.ID_OCORRENCIA = D.ID_OCORRENCIA "
			+ "INNER JOIN SEG_USUARIO AS C ON C.ID_USUARIO = B.ID_USUARIO "
			+ "INNER JOIN MED_IMPORTACAO AS I ON I.ID_IMPORTACAO = A.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS G ON G.ID_GRUPO_FATURAMENTO = I.ID_GRUPO_FATURAMENTO "
			+ "WHERE I.ID_REGIONAL = ?1 AND I.DT_ANO_MES_REF = ?2 AND G.CD_GRUPO_FATURAMENTO = ?3 AND A.ST_TIPOLEITURA = ?4 AND B.FL_ATIVO = 1 "
			+ "ORDER BY CONVERT(DATETIME, CONCAT(I.DT_PREVISAO_FIM, ' ', CONVERT(VARCHAR(8), B.DT_LEITURA, 114)), 20) ASC", nativeQuery = true)
	public List<RetornoLeituraExportacaoProjection> getRetornoLeituraExportacaoDataFim(Long idRegional, LocalDate dataReferencia, int grupoFaturamento, String tipoLeitura);

	@Transactional
	@Query(value = "SELECT B.ID_RETORNO_LEITURA AS IDRETORNOLEITURA, B.DT_LEITURA AS DATALEITURA, CONVERT(VARCHAR(8),B.DT_LEITURA,114) AS HORALEITURA, A.NR_LEITURISTA AS NUMEROLEITURISTA, A.NR_ORDEM_LEITURA AS ORDEMLEITURA, "
			+ "A.NR_UNIDADE_LEITURA AS UNIDADELEITURA, A.NM_CLIENTE AS CLIENTE, A.NM_ENDERECO AS ENDERECO, A.NM_COMPLEMENTO AS COMPLEMENTO, A.NM_MUNICIPIO AS MUNICIPIO, A.CD_CEP AS CEP, "
			+ "A.NR_INSTALACAO AS INSTALACAO, A.CD_LOGRADOURO AS CODIGOLOGRADOURO, A.NR_MEDIDOR AS MEDIDOR, A.CD_TIPO_MEDIDOR AS TIPOMEDIDOR, A.CD_SEQUENCIA AS SEQUENCIA, A.CD_UNIDADE_MEDIDA AS UNIDADEMEDIDA, "
			+ "A.DS_MENS_AVISO_MOBILE AS MENSAVISOMOBILE, A.CD_SEGUIMENTO AS SEGUIMENTO, A.CD_RAMO_ATIVIDADE AS RAMOATIVIDADE, A.NR_ULTIMA_LEITURA AS ULTIMALEITURA, A.NR_MEDIA3_MESES AS MEDIATRESMESES, "
			+ "CASE WHEN D.FL_TIPO_OCORRENCIA = 1 AND B.NR_LEITURA_MEDIDA > 0 THEN 0 ELSE B.NR_LEITURA_MEDIDA END AS LEITURAMEDIDA, "
			+ "D.CD_OCORRENCIA AS CODIGOOCORRENCIA, A.FL_LEITURA_REPASSE AS LEITURAREPASSE, A.CD_TAREFA_LEITURA AS TAREFALEITURA, B.CD_ORDENACAO_LEITURA AS ORDENACAOLEITURA, A.CD_TAREFA_ENTREGA AS TAREFAENTREGA, "
			+ "REPLICATE('0',8- LEN(C.NR_MATRICULA)) + RTRIM(C.NR_MATRICULA) AS MATRICULACOLABORADOR, "
			+ "CASE WHEN B.CD_LATITUDE != '0' THEN LEFT(B.CD_LATITUDE, 7) ELSE B.CD_LATITUDE END AS LATITUDE, "
			+ "CASE WHEN B.CD_LONGITUDE != '0' THEN LEFT(B.CD_LONGITUDE, 7) ELSE B.CD_LONGITUDE END AS LONGITUDE, "
			+ "B.ID_LEITURA AS IDLEITURA, B.ID_USUARIO AS IDCOLABORADOR, "
			+ "(CASE WHEN B.DS_OBSERVACAO IS NOT NULL THEN LEFT(B.DS_OBSERVACAO, 30) ELSE '' END) AS OBSERVACAO "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_RETORNO_LEITURA AS B ON A.ID_LEITURA = B.ID_LEITURA "
			+ "INNER JOIN MED_OCORRENCIA AS D ON B.ID_OCORRENCIA = D.ID_OCORRENCIA "
			+ "INNER JOIN SEG_USUARIO AS C ON C.ID_USUARIO = B.ID_USUARIO "
			+ "INNER JOIN MED_IMPORTACAO AS I ON I.ID_IMPORTACAO = A.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS G ON G.ID_GRUPO_FATURAMENTO = I.ID_GRUPO_FATURAMENTO "
			+ "WHERE I.ID_REGIONAL = ?1 AND I.DT_ANO_MES_REF = ?2 AND G.CD_GRUPO_FATURAMENTO = ?3 AND A.ST_TIPOLEITURA = ?4 AND B.FL_ATIVO = 1 "
			+ "ORDER BY B.DT_LEITURA ASC", nativeQuery = true)
	public List<RetornoLeituraExportacaoProjection> getRetornoLeituraExportacao(Long idRegional, LocalDate dataReferencia, int grupoFaturamento, String tipoLeitura);
	
	@Transactional
	@Query(value = "SELECT * FROM ("
			+ "SELECT D.DT_ANO_MES_REF AS DATAREFERENCIA, D.CD_TAREFA_LEITURA AS TAREFA, D.NR_INSTALACAO AS INSTALACAO, D.NR_MEDIDOR AS MEDIDOR, "
			+ "G.NM_NOME AS NOMEUSUARIO, F.CD_OCORRENCIA AS CODOCORRENCIA, F.NM_NOME AS OCORRENCIA, D.NR_LEITURA_ANTERIOR AS LEITURAANTERIOR, "
			+ "D.NR_LEITURA_MEDIDA AS LEITURAMEDIDA, A.NR_MEDIA3_MESES AS MEDIATRESMESES, "
			+ "CASE WHEN D.DT_ANO_MES_REF = DATEADD(MONTH, -2, ?1) THEN 'MESUM' "
			+ "WHEN D.DT_ANO_MES_REF = DATEADD(MONTH, -1, ?1) THEN 'MESDOIS' END AS ANOMES, "
			+ "@@ROWCOUNT AS QTD "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_IMPORTACAO AS B ON B.ID_IMPORTACAO = A.ID_IMPORTACAO "
			+ "INNER JOIN MED_RETORNO_LEITURA AS D ON D.ID_LEITURA = A.ID_LEITURA "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS E ON E.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "INNER JOIN MED_OCORRENCIA AS F ON F.ID_OCORRENCIA = D.ID_OCORRENCIA "
			+ "INNER JOIN SEG_USUARIO AS G ON G.ID_USUARIO = D.ID_USUARIO "
			+ "WHERE B.DT_ANO_MES_REF IN(DATEADD(MONTH, -1, ?1), DATEADD(MONTH, -2, ?1)) "
			+ "AND B.ID_REGIONAL = ?2 AND B.ID_GRUPO_FATURAMENTO = ?3 AND D.NR_INSTALACAO = ?4 AND D.NR_MEDIDOR = ?5 AND D.FL_ATIVO = 1 "
			+ ") LINHAS "
			+ "PIVOT (COUNT(QTD) FOR ANOMES IN([MESUM], [MESDOIS], [MESATUAL])) COLUNAS ", nativeQuery = true)
	public List<RetornoLeituraProjection> getUltimosTresMeses(LocalDate dataReferencia, Long idRegional, long idGrupoFaturamento, String instalacao, String medidor);
	
	@Transactional
	@Query(value = "SELECT ID_LEITURA FROM MED_RETORNO_LEITURA WHERE ID_LEITURA IN(?1) ", nativeQuery = true)
	public List<Long> getListaIdLeitura(List<Long> lista);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE MED_RETORNO_LEITURA SET FL_FOTO = 1 WHERE ID_RETORNO_LEITURA = ?1", nativeQuery = true)
	public void marcarComFoto(long idRetornoLeitura);
	
	@Transactional
	@Query(value = "SELECT TOP 1 "
			+ "	A.ID_LEITURA			AS IDLEITURA "
			+ "	,A.NR_INSTALACAO		AS INSTALACAO "
			+ "	,A.NR_MEDIDOR			AS MEDIDOR "
			+ "	,C.NR_LEITURA_MEDIDA	AS LEITURAMEDIDA "
			+ "	,C.DT_LEITURA			AS DATALEITURA "
			+ "	,A.CD_SEGUIMENTO		AS SEGMENTO "
			+ "	,A.CD_RAMO_ATIVIDADE	AS RAMOATIVIDADE " 
			+ "	,A.NM_ENDERECO			AS ENDERECO "
			+ "	,A.NM_COMPLEMENTO		AS COMPLEMENTO "
			+ "	,A.NM_MUNICIPIO			AS MUNICIPIO "
			+ "	,C.FL_FOTO				AS ISFOTO "
			+ "	,C.CD_LATITUDE			AS LATITUDE "
			+ "	,C.CD_LONGITUDE			AS LONGITUDE "
			+ "FROM "
			+ "	MED_LEITURA								AS A "
			+ "	INNER JOIN MED_IMPORTACAO				AS B ON B.ID_IMPORTACAO = A.ID_IMPORTACAO "
			+ "	INNER JOIN MED_RETORNO_LEITURA			AS C ON C.ID_LEITURA = A.ID_LEITURA "
			+ "	INNER JOIN MED_GRUPO_FATURAMENTO		AS D ON D.ID_GRUPO_FATURAMENTO = C.ID_GRUPO_FATURAMENTO "
			+ "WHERE "
			+ "	B.ID_REGIONAL = ?1 "
			+ "	AND B.DT_ANO_MES_REF = ?2 "
		//	+ "	AND D.CD_GRUPO_FATURAMENTO = ?3 "
			+ "	AND A.NR_INSTALACAO LIKE CASE WHEN ?3 != '0' THEN CONCAT('%', ?3) ELSE '%' END "
			+ "	AND A.NR_MEDIDOR LIKE CASE WHEN ?4 != '0' THEN CONCAT('%', ?4) ELSE '%' END "
			+ "	AND C.FL_ATIVO = 1", nativeQuery = true)
	public RetornoLeituraClienteProjection getRetornoLeituraCliente(Long idRegional, LocalDate dataReferencia, String instalacao, String medidor);
	
	@Modifying
	@Transactional
	@Query(value = "IINSERT INTO MED_RETORNO_LEITURA ("
			+ "	 ID_LEITURA "
			+ "	,ID_REGIONAL "
			+ "	,ID_USUARIO "
			+ "	,ID_OCORRENCIA "
			+ "	,ID_GRUPO_FATURAMENTO "
			+ "	,DT_ANO_MES_REF "
			+ "	,NR_LEITURA_MEDIDA "
			+ "	,NR_VARIACAO_LEITURA "
			+ "	,DT_LEITURA "
			+ "	,CD_TAREFA_LEITURA "
			+ "	,CD_ORDENACAO_LEITURA "
			+ "	,CD_TAREFA_ENTREGA "
			+ "	,CD_LATITUDE "
			+ "	,CD_LONGITUDE "
			+ "	,FL_CRITICA "
			+ "	,DS_OBSERVACAO "
			+ "	,FL_MEDIA "
			+ "	,NR_INSTALACAO "
			+ "	,NR_LEITURA_ANTERIOR "
			+ "	,NR_MEDIDOR "
			+ "	,FL_FOTO "
			+ "	,FL_ATIVO  "
			+ "	) "
			+ "SELECT "
			+ "	?1 AS ID_LEITURA"
			+ "	,B.ID_REGIONAL "
			+ "	,?2 AS ID_USUARIO "
			+ "	,?3 AS ID_OCORRENCIA "
			+ "	,B.ID_GRUPO_FATURAMENTO "
			+ "	,B.DT_ANO_MES_REF "
			+ "	,?4 AS LEITURA_MEDIDA "
			+ "	,?5 AS VARIACAO_LEITURA "
			+ "	,?6 AS DATA_LEITURA "
			+ "	,A.CD_TAREFA_LEITURA "
			+ "	,A.CD_ORDENACAO_LEITURA "
			+ "	,A.CD_TAREFA_ENTREGA "
			+ "	,?7 AS LATITUDE "
			+ "	,?8 AS LONGITUDE "
			+ "	,?9 AS CRITICA "
			+ "	,?10 AS OBSERVACAO "
			+ "	,?11 AS MEDIA "
			+ "	,A.NR_INSTALACAO "
			+ "	,A.NR_ULTIMA_LEITURA "
			+ "	,A.NR_MEDIDOR "
			+ "	,?12 AS FOTO "
			+ "	,?13 AS ATIVO "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_IMPORTACAO AS B ON B.ID_IMPORTACAO = A.ID_IMPORTACAO "
			+ "LEFT OUTER JOIN MED_RETORNO_LEITURA AS C ON C.ID_LEITURA = A.ID_LEITURA "
			+ "WHERE A.ID_LEITURA = ?1 AND C.ID_LEITURA IS NULL ", nativeQuery = true)
	public RetornoLeitura lancarLeitura(Long idLeitura, Long idUsuario, Long idOcorrencia, Integer leituraMedida, Double variacao, LocalDateTime dataLeitura, String latitude, String longitude, Integer critica, String observacao, Integer media, Integer isFoto, Integer ativo);
	
}
