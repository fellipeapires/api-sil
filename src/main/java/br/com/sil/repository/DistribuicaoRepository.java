package br.com.sil.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.sil.model.Distribuicao;
import br.com.sil.repository.distribuicao.DistribuicaoRepositoryQuery;
import br.com.sil.repository.projection.CargaMobileProjection;
import br.com.sil.repository.projection.DesassociadoProjection;
import br.com.sil.repository.projection.DistribuicaoProjection;
import br.com.sil.repository.projection.DistribuidoDetailProjection;
import br.com.sil.repository.projection.DistribuidoProjection;

public interface DistribuicaoRepository extends JpaRepository<Distribuicao, Long>, DistribuicaoRepositoryQuery {

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO MED_DISTRIBUICAO_LEITURA_REGISTRO (ID_LEITURA, ID_REGIONAL, ID_USUARIO, FL_ASSOCIADO, DT_DISTRIBUICAO) "
			+ "SELECT A.ID_LEITURA, ?1, ?4, ?8, ?9 " 
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_IMPORTACAO AS B ON A.ID_IMPORTACAO = B.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS D ON D.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "LEFT OUTER JOIN MED_DISTRIBUICAO_LEITURA_REGISTRO AS C ON C.ID_LEITURA = A.ID_LEITURA "
			+ "LEFT OUTER JOIN MED_RETORNO_LEITURA AS E ON E.ID_LEITURA = A.ID_LEITURA "
			+ "WHERE B.ID_REGIONAL = ?1 AND B.DT_ANO_MES_REF = ?2 AND D.CD_GRUPO_FATURAMENTO = ?3 AND A.CD_TAREFA_LEITURA = ?5 "
			+ "AND RTRIM(LTRIM(LEFT(A.NM_ENDERECO, 62))) = ISNULL(?6, RTRIM(LTRIM(LEFT(A.NM_ENDERECO, 62)))) "
			+ "AND A.ID_LEITURA = ISNULL((CASE WHEN ?7 = 0 THEN NULL ELSE ?7 END), A.ID_LEITURA) "
			+ "AND C.ID_DISTRIBUICAO_LEITURA_REGISTRO IS NULL AND E.ID_LEITURA IS NULL ", nativeQuery = true)
	public int incluir(Long idRegional, LocalDate dataReferencia, int grupoFaturamento, Long idUsuario, String tarefaLeitura, String endereco, Long idLeitura, int associado, LocalDateTime dataDistribuicao);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO MED_DISTRIBUICAO_LEITURA_REGISTRO (ID_LEITURA, ID_REGIONAL, ID_USUARIO, FL_ASSOCIADO, DT_DISTRIBUICAO) "
			+ "SELECT A.ID_LEITURA, ?1, ?5, ?7, ?8 "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_IMPORTACAO AS B ON A.ID_IMPORTACAO = B.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS D ON D.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "INNER JOIN MED_DISTRIBUICAO_LEITURA_REGISTRO AS E ON E.ID_LEITURA = A.ID_LEITURA "
			+ "LEFT OUTER JOIN MED_RETORNO_LEITURA AS C ON C.ID_LEITURA = A.ID_LEITURA "
			+ "WHERE B.ID_REGIONAL = ?1 AND B.DT_ANO_MES_REF = ?2 AND D.CD_GRUPO_FATURAMENTO = ?3 AND A.CD_TAREFA_LEITURA = ?4 "
			+ "AND E.ID_USUARIO = ?6 AND C.ID_RETORNO_LEITURA IS NULL AND E.ID_LEITURA NOT IN( "
			+ "SELECT A.ID_LEITURA "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_IMPORTACAO AS B ON A.ID_IMPORTACAO = B.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS D ON D.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "INNER JOIN MED_DISTRIBUICAO_LEITURA_REGISTRO AS E ON E.ID_LEITURA = A.ID_LEITURA "
			+ "WHERE B.ID_REGIONAL = ?1 AND B.DT_ANO_MES_REF = ?2 AND E.ID_USUARIO = ?5 "
			+ "AND D.CD_GRUPO_FATURAMENTO = ?3 AND A.CD_TAREFA_LEITURA = ?4 "
			+ ")", nativeQuery = true)
	public Integer duplicar(Long idRegional, LocalDate dataReferencia, int grupoFaturamento, String tarefaLeitura, Long idUsuario, Long idUsuarioAtribuido, int associado, LocalDateTime dataDistribuicao);
	
	@Transactional
	@Query(value="SELECT D.ID_USUARIO AS IDUSUARIO, A.ID_LEITURA AS IDLEITURA "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_IMPORTACAO AS B ON B.ID_IMPORTACAO = A.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS C ON C.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "INNER JOIN MED_DISTRIBUICAO_LEITURA_REGISTRO AS D ON D.ID_LEITURA = A.ID_LEITURA "
			+ "LEFT OUTER JOIN MED_RETORNO_LEITURA AS E ON E.ID_LEITURA = D.ID_LEITURA "
			+ "WHERE B.ID_REGIONAL = ?1 AND B.DT_ANO_MES_REF = ?2 AND C.CD_GRUPO_FATURAMENTO = ?3 "
			+ "AND A.CD_TAREFA_LEITURA = ?4 AND D.ID_USUARIO = ?5 AND D.FL_ASSOCIADO = 1 AND E.ID_RETORNO_LEITURA IS NULL ", nativeQuery = true)
	public List<DesassociadoProjection> getDesassociado(Long idRegional, String dataReferencia, int grupoFaturamento, String tarefa, Long idUsuario);
	
	@Transactional
	@Query(value="SELECT A.ID_USUARIO AS IDUSUARIO, A.ID_LEITURA AS IDLEITURA "
			+ "FROM MED_DISTRIBUICAO_LEITURA_REGISTRO AS A "
			+ "LEFT OUTER JOIN MED_RETORNO_LEITURA AS B ON B.ID_LEITURA = A.ID_LEITURA "
			+ "WHERE A.ID_DISTRIBUICAO_LEITURA_REGISTRO = ?1 AND A.FL_ASSOCIADO = 1 AND B.ID_LEITURA IS NULL ", nativeQuery = true)
	public DesassociadoProjection getDesassociadoIndividual(Long idDistribuicao);
	
	@Modifying
	@Transactional
	@Query(value="DELETE MED_DISTRIBUICAO_LEITURA_REGISTRO "
			+ "FROM MED_DISTRIBUICAO_LEITURA_REGISTRO AS A "
			+ "INNER JOIN MED_LEITURA AS B ON B.ID_LEITURA = A.ID_LEITURA "
			+ "INNER JOIN MED_IMPORTACAO AS C ON C.ID_IMPORTACAO = B.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS D ON D.ID_GRUPO_FATURAMENTO = C.ID_GRUPO_FATURAMENTO "
			+ "LEFT OUTER JOIN MED_RETORNO_LEITURA AS E ON E.ID_LEITURA = A.ID_LEITURA "
			+ "WHERE A.ID_REGIONAL = ?1 AND C.DT_ANO_MES_REF = ?2 AND D.CD_GRUPO_FATURAMENTO = ?3 "
			+ "AND B.CD_TAREFA_LEITURA = ?4 AND A.ID_USUARIO = ?5 AND E.ID_RETORNO_LEITURA IS NULL  ", nativeQuery = true)
	public Integer desassociar(Long idRegional, String dataReferencia, int grupoFaturamento, String tarefa, Long idUsuario);
	
	@Modifying
	@Transactional
	@Query(value="DELETE MED_DISTRIBUICAO_LEITURA_REGISTRO "
			+ "FROM MED_DISTRIBUICAO_LEITURA_REGISTRO AS A "
			+ "LEFT OUTER JOIN MED_RETORNO_LEITURA AS B ON B.ID_LEITURA = A.ID_LEITURA "
			+ "WHERE A.ID_DISTRIBUICAO_LEITURA_REGISTRO = ?1 AND B.ID_LEITURA IS NULL  ", nativeQuery = true)
	public Integer desassociarIndividual(Long idDitribuicao);
	
	@Modifying
	@Transactional
	@Query(value="INSERT INTO MED_DESASSOCIADO (ID_USUARIO, ID_LEITURA) VALUES (?1, ?2)", nativeQuery = true)
	public void incluirDesassociado(Long idUsuario, Long idLeitura);
	
	@Modifying
	@Transactional
	@Query(value="DELETE MED_DESASSOCIADO WHERE ID_USUARIO=?1 AND ID_LEITURA=?2", nativeQuery = true)
	public void limparDesassociado(Integer idUsuario, Long idLeitura);
	
	@Transactional
	@Query(value="SELECT B.ID_REGIONAL AS IDREGIONAL, D.CD_GRUPO_FATURAMENTO AS GRUPOFATURAMENTO, B.DT_ANO_MES_REF AS DATAREFERENCIA, "
			+ "A.CD_TAREFA_LEITURA AS TAREFA, E.ID_USUARIO AS IDUSUARIO, E.NM_NOME AS NOMEUSUARIO, COUNT(*) AS QTD "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_IMPORTACAO AS B ON A.ID_IMPORTACAO = B.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS D ON D.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "INNER JOIN MED_DISTRIBUICAO_LEITURA_REGISTRO AS C ON C.ID_LEITURA = A.ID_LEITURA "
			+ "INNER JOIN SEG_USUARIO AS E ON E.ID_USUARIO = C.ID_USUARIO "
			+ "WHERE B.DT_ANO_MES_REF = ?1 AND B.ID_REGIONAL = ?2 AND D.CD_GRUPO_FATURAMENTO = ?3 "
			+ "GROUP BY B.ID_REGIONAL, D.CD_GRUPO_FATURAMENTO, B.DT_ANO_MES_REF, A.CD_TAREFA_LEITURA, E.ID_USUARIO, E.NM_NOME "
			+ "ORDER BY E.NM_NOME, A.CD_TAREFA_LEITURA ASC", nativeQuery = true)
	public List<DistribuidoProjection> getDistribuido(LocalDate dataReferencia, Long idRegional, Integer grupoFaturamento);
	
	@Transactional
	@Query(value="SELECT "
			+ "	D.ID_DISTRIBUICAO_LEITURA_REGISTRO AS IDDISTRIBUICAO "
			+ "	,A.NR_INSTALACAO AS INSTALACAO "
			+ "	,A.NR_MEDIDOR AS MEDIDOR "
			+ "	,CONCAT(RTRIM(LTRIM(LEFT(A.NM_ENDERECO, 62))), ', ', RTRIM(LTRIM(SUBSTRING(A.NM_ENDERECO, 62, 10))), ' ', RTRIM(LTRIM(RIGHT(A.NM_ENDERECO, 10)))) AS ENDERECO "
			+ "	,RTRIM(LTRIM(A.NM_COMPLEMENTO)) AS COMPLEMENTO "
			+ "	,RTRIM(LTRIM(A.NM_MUNICIPIO)) AS MUNICIPIO "
			+ "	,CASE WHEN E.ID_LEITURA IS NOT NULL THEN 1 ELSE 0 END AS STATUS "
			+ "FROM "
			+ "	MED_LEITURA AS A "
			+ "	INNER JOIN MED_IMPORTACAO AS B ON B.ID_IMPORTACAO = A.ID_IMPORTACAO "
			+ "	INNER JOIN MED_GRUPO_FATURAMENTO AS C ON C.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "	INNER JOIN MED_DISTRIBUICAO_LEITURA_REGISTRO AS D ON D.ID_LEITURA = A.ID_LEITURA "
			+ "	LEFT OUTER JOIN MED_RETORNO_LEITURA AS E ON E.ID_LEITURA =  A.ID_LEITURA AND E.FL_ATIVO = 1 "
			+ "WHERE "
			+ "	B.ID_REGIONAL = ?1 "
			+ "	AND B.DT_ANO_MES_REF = ?2 "
			+ "	AND C.CD_GRUPO_FATURAMENTO = ?3 "
			+ "	AND A.CD_TAREFA_LEITURA = ?4 "
			+ "	AND D.ID_USUARIO = ?5 "
			+ "ORDER BY "
			+ "	STATUS "
			+ "	,CONCAT(RTRIM(LTRIM(LEFT(A.NM_ENDERECO, 62))), ', ', RTRIM(LTRIM(SUBSTRING(A.NM_ENDERECO, 62, 10))), ' ', RTRIM(LTRIM(RIGHT(A.NM_ENDERECO, 10)))) ASC", nativeQuery = true)
	public List<DistribuidoDetailProjection> getDistribuidoDetail(Long idRegional, LocalDate dataReferencia, int grupoFaturamento, String tarefa, long idUsuario);
	
	@Transactional
	@Query(value = "SELECT 0 AS IDLEITURA, B.ID_REGIONAL AS IDREGIONAL, D.CD_GRUPO_FATURAMENTO AS GRUPOFATURAMENTO, B.DT_ANO_MES_REF AS DATAREFERENCIA, "
			+ "A.CD_TAREFA_LEITURA AS TAREFA, RTRIM(LTRIM(A.NM_MUNICIPIO)) AS LOCALIDADE, COUNT(*) AS QTD "
			+ "FROM MED_LEITURA AS A " 
			+ "INNER JOIN MED_IMPORTACAO AS B ON A.ID_IMPORTACAO = B.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS D ON D.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "LEFT OUTER JOIN MED_DISTRIBUICAO_LEITURA_REGISTRO AS C ON C.ID_LEITURA = A.ID_LEITURA "
			+ "LEFT OUTER JOIN MED_RETORNO_LEITURA AS E ON E.ID_LEITURA = A.ID_LEITURA "
			+ "WHERE B.DT_ANO_MES_REF = ?1 AND B.ID_REGIONAL = ?2 AND D.CD_GRUPO_FATURAMENTO = ?3 "
			+ "AND C.ID_DISTRIBUICAO_LEITURA_REGISTRO IS NULL AND E.ID_LEITURA IS NULL " 
			+ "GROUP BY A.CD_TAREFA_LEITURA, A.NM_MUNICIPIO, B.ID_REGIONAL, D.CD_GRUPO_FATURAMENTO , B.DT_ANO_MES_REF "
			+ "ORDER BY A.CD_TAREFA_LEITURA, A.NM_MUNICIPIO ASC", nativeQuery = true)
	public List<DistribuicaoProjection> getPendentesPorTarefa(LocalDate dataReferencia, Long idRegional, Integer grupoFaturamento);

	@Transactional
	@Query(value = "SELECT 0 AS IDLEITURA, B.ID_REGIONAL AS IDREGIONAL, D.CD_GRUPO_FATURAMENTO AS GRUPOFATURAMENTO, B.DT_ANO_MES_REF AS DATAREFERENCIA, "
			+ "A.CD_TAREFA_LEITURA AS TAREFA, RTRIM(LTRIM(LEFT(A.NM_ENDERECO, 62))) AS ENDERECO, RTRIM(LTRIM(A.NM_MUNICIPIO)) AS LOCALIDADE, COUNT(*) AS QTD "
			+ "FROM MED_LEITURA AS A " 
			+ "INNER JOIN MED_IMPORTACAO AS B ON A.ID_IMPORTACAO = B.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS D ON D.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "LEFT OUTER JOIN MED_DISTRIBUICAO_LEITURA_REGISTRO AS C ON C.ID_LEITURA = A.ID_LEITURA "
			+ "LEFT OUTER JOIN MED_RETORNO_LEITURA AS E ON E.ID_LEITURA = A.ID_LEITURA "
			+ "WHERE B.DT_ANO_MES_REF = ?1 AND B.ID_REGIONAL = ?2 AND D.CD_GRUPO_FATURAMENTO = ?3 "
			+ "AND C.ID_DISTRIBUICAO_LEITURA_REGISTRO IS NULL AND E.ID_LEITURA IS NULL "
			+ "GROUP BY A.CD_TAREFA_LEITURA, A.NM_MUNICIPIO, LEFT(A.NM_ENDERECO, 62), B.ID_REGIONAL, D.CD_GRUPO_FATURAMENTO, B.DT_ANO_MES_REF "
			+ "ORDER BY A.CD_TAREFA_LEITURA, A.NM_MUNICIPIO, LEFT(A.NM_ENDERECO, 62) ASC", nativeQuery = true)
	public List<DistribuicaoProjection> getPendentesPorEndereco(LocalDate dataReferencia, Long idRegional, Integer grupoFaturamento);

	@Transactional
	@Query(value = "SELECT A.ID_LEITURA AS IDLEITURA, B.ID_REGIONAL AS IDREGIONAL, D.CD_GRUPO_FATURAMENTO AS GRUPOFATURAMENTO, "
			+ "B.DT_ANO_MES_REF AS DATAREFERENCIA, A.CD_TAREFA_LEITURA AS TAREFA, "
			+ "CONCAT(RTRIM(LTRIM(LEFT(A.NM_ENDERECO, 62))), ', ', RTRIM(LTRIM(SUBSTRING(A.NM_ENDERECO, 62, 10))), ' ', RTRIM(LTRIM(RIGHT(A.NM_ENDERECO, 10)))) AS ENDERECO, "
			+ "RTRIM(LTRIM(A.NM_MUNICIPIO)) AS LOCALIDADE, COUNT(*) AS QTD "
			+ "FROM MED_LEITURA AS A " 
			+ "INNER JOIN MED_IMPORTACAO AS B ON A.ID_IMPORTACAO = B.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS D ON D.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "LEFT OUTER JOIN MED_DISTRIBUICAO_LEITURA_REGISTRO AS C ON C.ID_LEITURA = A.ID_LEITURA "
			+ "LEFT OUTER JOIN MED_RETORNO_LEITURA AS E ON E.ID_LEITURA = A.ID_LEITURA "
			+ "WHERE B.DT_ANO_MES_REF = ?1 AND B.ID_REGIONAL = ?2 AND D.CD_GRUPO_FATURAMENTO = ?3 "
			+ "AND C.ID_DISTRIBUICAO_LEITURA_REGISTRO IS NULL AND E.ID_LEITURA IS NULL "
			+ "GROUP BY A.ID_LEITURA, A.CD_TAREFA_LEITURA, B.ID_REGIONAL, D.CD_GRUPO_FATURAMENTO, B.DT_ANO_MES_REF, A.NR_INSTALACAO , "
			+ "CONCAT(RTRIM(LTRIM(LEFT(A.NM_ENDERECO, 62))), ', ', RTRIM(LTRIM(SUBSTRING(A.NM_ENDERECO, 62, 10))), ' ', RTRIM(LTRIM(RIGHT(A.NM_ENDERECO, 10)))), A.NM_MUNICIPIO "
			+ "ORDER BY A.CD_TAREFA_LEITURA, A.NM_MUNICIPIO, CONCAT(RTRIM(LTRIM(LEFT(A.NM_ENDERECO, 62))), ', ', RTRIM(LTRIM(SUBSTRING(A.NM_ENDERECO, 62, 10))), ' ', RTRIM(LTRIM(RIGHT(A.NM_ENDERECO, 10)))) ASC ", nativeQuery = true)
	public List<DistribuicaoProjection> getPendentesPorInstalacao(LocalDate dataReferencia, Long idRegional, Integer grupoFaturamento);
	
	@Transactional
	@Query(value="SELECT B.ID_REGIONAL AS IDREGIONAL, D.CD_GRUPO_FATURAMENTO AS GRUPOFATURAMENTO, B.DT_ANO_MES_REF AS DATAREFERENCIA, "
			+ "A.CD_TAREFA_LEITURA AS TAREFA, E.ID_USUARIO AS IDUSUARIO, E.NM_NOME AS NOMEUSUARIO, "
			+ "CASE WHEN C.FL_ASSOCIADO = 3 THEN 'NAO LIBERADO' WHEN C.FL_ASSOCIADO = 2 THEN 'LIBERADO' WHEN C.FL_ASSOCIADO = 1 THEN 'NO COLETOR' END AS STATUS, COUNT(*) AS QTD "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_IMPORTACAO AS B ON A.ID_IMPORTACAO = B.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS D ON D.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "INNER JOIN MED_DISTRIBUICAO_LEITURA_REGISTRO AS C ON C.ID_LEITURA = A.ID_LEITURA "
			+ "INNER JOIN SEG_USUARIO AS E ON E.ID_USUARIO = C.ID_USUARIO "
			+ "WHERE B.DT_ANO_MES_REF = ?1 AND B.ID_REGIONAL = ?2 AND D.CD_GRUPO_FATURAMENTO = ?3 "
			+ "GROUP BY A.CD_TAREFA_LEITURA, B.ID_REGIONAL, D.CD_GRUPO_FATURAMENTO, B.DT_ANO_MES_REF, E.ID_USUARIO, E.NM_NOME, C.FL_ASSOCIADO "
			+ "ORDER BY C.FL_ASSOCIADO DESC ", nativeQuery = true)
	public List<DistribuidoProjection> listarCarga(LocalDate dataReferencia, long idRegional, int grupoFaturamento);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE MED_DISTRIBUICAO_LEITURA_REGISTRO SET FL_ASSOCIADO = ?6 "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_IMPORTACAO AS B ON A.ID_IMPORTACAO = B.ID_IMPORTACAO "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS D ON D.ID_GRUPO_FATURAMENTO = B.ID_GRUPO_FATURAMENTO "
			+ "INNER JOIN MED_DISTRIBUICAO_LEITURA_REGISTRO AS C ON C.ID_LEITURA = A.ID_LEITURA "
			+ "INNER JOIN SEG_USUARIO AS E ON E.ID_USUARIO = C.ID_USUARIO "
			+ "WHERE B.DT_ANO_MES_REF = ?1 AND B.ID_REGIONAL = ?2 AND D.CD_GRUPO_FATURAMENTO = ?3  AND C.FL_ASSOCIADO = ?7 "
			+ "AND A.CD_TAREFA_LEITURA = ISNULL((CASE WHEN ?4 = '0' THEN NULL ELSE ?4 END), A.CD_TAREFA_LEITURA) "
			+ "AND C.ID_USUARIO = ISNULL((CASE WHEN ?5 = 0 THEN NULL ELSE ?5 END), C.ID_USUARIO)", nativeQuery = true)
	public Integer liberarCarga(LocalDate dataReferencia, long idRegional, int grupoFaturamento, String tarefa, long idUsuario, int liberado, int naoLiberado);
	
	@Transactional
	@Query(value="SELECT A.ID_LEITURA "
			+ "FROM MED_DESASSOCIADO AS A "
			+ "LEFT OUTER JOIN MED_RETORNO_LEITURA AS B ON B.ID_LEITURA = A.ID_LEITURA "
			+ "WHERE A.ID_USUARIO = ?1 AND B.ID_RETORNO_LEITURA IS NULL ", nativeQuery = true)
	public List<Long> getDesassociadoMobile(Long idUsuario);
	
	@Transactional
	@Query(value="SELECT  A.ID_LEITURA AS ID, 0 AS IDUSUARIO, 0 AS QTDEREGISTROS, 0 AS PAGINA, A.NR_ORDEM_LEITURA AS ORDEMLEITURA, 0 AS OCORRENCIA, 0 AS TOTALCEP, '' AS LAGRADOURO, "
			+ "A.ID_GRUPO_FATURAMENTO AS IDGRUPOFATURAMENTO, D.CD_GRUPO_FATURAMENTO AS GRUPOFATURAMENTO, A.NR_UNIDADE_LEITURA AS UNIDLEITURA, A.NM_CLIENTE AS CLIENTE, '' AS APTO, "
			+ "A.NM_ENDERECO AS ENDERECO, A.NM_COMPLEMENTO AS COMPLEMENTO, A.NM_MUNICIPIO AS MUNICIPIO, A.CD_CEP AS CEP, A.NR_INSTALACAO AS INSTALACAO, A.CD_SEQUENCIA AS SEQUENCIA,"
			+ "A.NR_MEDIDOR AS MEDIDOR, A.CD_TIPO_MEDIDOR AS TIPOMEDIDOR, A.DS_MENS_AVISO_MOBILE AS MENSAVISOMOBILE, A.CD_RAMO_ATIVIDADE AS RAMOATIVIDADE, "
			+ "CASE WHEN A.CD_SEGUIMENTO = '01' THEN 'RESIDENCIAL' WHEN A.CD_SEGUIMENTO = '02' THEN 'COMERCIO' "
			+ "WHEN A.CD_SEGUIMENTO = '13' THEN 'COLETIVO' WHEN A.CD_SEGUIMENTO = '19' THEN 'REFRIGERACAO' "
			+ "WHEN A.CD_SEGUIMENTO = '03' OR A.CD_SEGUIMENTO = '07' OR A.CD_SEGUIMENTO = '18' THEN 'OUTROS' ELSE '' END AS SEGMENTO, "
			+ "A.NR_ULTIMA_LEITURA AS ULTIMALEITURA, A.NR_MEDIA3_MESES AS MEDIA3MESES, A.NR_LEITURA_MEDIDA AS LEITURAMEDIDA, A.FL_LEITURA_REPASSE AS FLAGLEITURAREPASSE, "
			+ "A.CD_TAREFA_LEITURA AS TAREFALEITURA, A.CD_TAREFA_ENTREGA AS TAREFAENTREGA, A.NR_MATRICULA_LEITURISTA AS MATRILEITURISTA, A.CD_ORDENACAO_LEITURA AS ORDENACAOLEITURA, "
			+ "A.ID_IMPORTACAO AS IDIMPORTACAO, A.CD_FAIXAMINIMA AS FAIXAMINIMA, A.CD_FAIXAMAXIMA AS FAIXAMAXIMA, A.ST_TIPOLEITURA AS TIPOLEITURA, "
			+ "0 AS IDDISTRIBUICAOMOBILE, 1 AS SITUACAODISTRIBUICAOREGISTRO, C.ID_USUARIO AS IDFUNCIONARIO, C.ID_DISTRIBUICAO_LEITURA_REGISTRO AS IDDISTRIBUICAOREGISTRO, "
			+ "(SELECT TOP 1 Y.DS_OBSERVACAO FROM MED_IMPORTACAO Z INNER JOIN MED_LEITURA X ON Z.ID_IMPORTACAO= X.ID_IMPORTACAO "
			+ "AND Z.DT_ANO_MES_REF = DATEADD(MONTH,-1, B.DT_ANO_MES_REF) "
			+ "INNER JOIN MED_RETORNO_LEITURA Y ON X.ID_LEITURA = Y.ID_LEITURA "
			+ "WHERE X.NR_INSTALACAO = A.NR_INSTALACAO AND X.CD_TAREFA_LEITURA = A.CD_TAREFA_LEITURA AND "
			+ "(Y.DS_OBSERVACAO != '' OR Y.DS_OBSERVACAO IS NOT NULL) ORDER BY Y.DS_OBSERVACAO DESC) AS OBSERVACAO "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN MED_IMPORTACAO AS B ON B.ID_IMPORTACAO = A.ID_IMPORTACAO "
			+ "INNER JOIN MED_DISTRIBUICAO_LEITURA_REGISTRO AS C ON C.ID_LEITURA = A.ID_LEITURA "
			+ "INNER JOIN MED_GRUPO_FATURAMENTO AS D ON D.ID_GRUPO_FATURAMENTO = A.ID_GRUPO_FATURAMENTO "
			+ "WHERE C.FL_ASSOCIADO = 2 AND C.ID_USUARIO = ?1 "
			+ "ORDER BY A.CD_TAREFA_LEITURA, A.NR_ORDEM_LEITURA ", nativeQuery = true)
	public List<CargaMobileProjection> getCargaMobile(Long idUsuario);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE MED_DISTRIBUICAO_LEITURA_REGISTRO SET FL_ASSOCIADO = 1 WHERE ID_USUARIO = ?1 AND ID_LEITURA IN(?2)", nativeQuery = true)
	public void alterarAssociadoMobile(long idUsuario, List<Long> listaIdLeitura);
	
	@Modifying
	@Transactional
	@Query(value="DELETE MED_DESASSOCIADO WHERE ID_USUARIO = ?1 AND ID_LEITURA IN(?2)", nativeQuery = true)
	public void zerarDesassociadoMobile(long idUsuario, List<Long> listaIdLeitura);
	
}
