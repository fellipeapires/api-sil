package br.com.sil.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.sil.model.Permissao;
import br.com.sil.repository.permissao.PermissaoRepositoryQuery;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>, PermissaoRepositoryQuery {
	public Optional<Permissao> findByNome(String nome);
	
	@Transactional
	@Query(value="SELECT * FROM SEG_PERMISSAO WHERE NM_PERMISSAO IN("
			+ "'ROLE_ADMINISTRATIVO',"
			+ "'ROLE_PESQUISAR',"
			+ "'ROLE_LEITURA',"
			+ "'ROLE_RETORNO_LEITURA',"
			//+ "'ROLE_PAINEL_CONFIGURACAO',"
			+ "'ROLE_HOME',"
			+ "'ROLE_PESQUISA_RETORNO_LEITURA',"
			+ "'ROLE_IMPORTACAO',"
			+ "'ROLE_DISTRIBUICAO',"
			+ "'ROLE_LIBERACAO_CARGA',"
			//+ "'ROLE_LANCAMENTO_LEITURA',"
			+ "'ROLE_ACOMPANHAMENTO',"
			+ "'ROLE_ROTEIRO',"
			+ "'ROLE_EMPRESA',"
			+ "'ROLE_LOTE',"
			+ "'ROLE_OCORRENCIA',"
			+ "'ROLE_PERFIL_ACESSO',"
			+ "'ROLE_REGIONAL',"
			+ "'ROLE_USUARIO',"
			//+ "'ROLE_REPASSE',"
			+ "'ROLE_EXPORTACAO',"
			+ "'ROLE_AUDITORIA',"
			+ "'ROLE_JORNADA_COLABORADOR') AND CD_SITUACAO = 1", nativeQuery = true)
	public List<Permissao> getPermissoesCrudUser();
	
	@Transactional
	@Query(value="SELECT " 
			+ "	 A.ID_PERMISSAO											AS ID_PERMISSAO "
			+ "	,A.NM_PERMISSAO											AS NM_PERMISSAO "
			+ "	,A.CD_SITUACAO											AS CD_SITUACAO "
			+ "FROM "
			+ "	SEG_PERMISSAO AS A "
			+ "	INNER JOIN USUARIO_PERMISSAO AS B ON B.ID_PERMISSAO = A.ID_PERMISSAO "
			+ "WHERE "
			+ "	B.ID_USUARIO = ?1 "
			+ "GROUP BY "
			+ "	 A.ID_PERMISSAO "
			+ "	,A.NM_PERMISSAO "
			+ "	,A.CD_SITUACAO	", nativeQuery = true)
	public List<Permissao> getPermissoesAssociadasUsuario(Long idUsuario);
	
	@Transactional
	@Query(value="SELECT "
			+ "	 ID_PERMISSAO											AS ID_PERMISSAO "
			+ "	,NM_PERMISSAO											AS NM_PERMISSAO "
			+ "	,CD_SITUACAO											AS CD_SITUACAO "
			+ "FROM "
			+ "	SEG_PERMISSAO "
			+ "WHERE ID_PERMISSAO NOT IN("
			+ "	SELECT "
			+ "		A.ID_PERMISSAO "
			+ "	FROM "
			+ "		SEG_PERMISSAO AS A "
			+ "		INNER JOIN USUARIO_PERMISSAO AS B ON B.ID_PERMISSAO = A.ID_PERMISSAO "
			+ "	WHERE "
			+ "		B.ID_USUARIO = ?1 "
			+ "	GROUP BY "
			+ "		A.ID_PERMISSAO "
			+ "		,A.NM_PERMISSAO "
			+ "		,A.CD_SITUACAO	"
			+ "	)"
			+ "GROUP BY "
			+ "	ID_PERMISSAO "
			+ "	,NM_PERMISSAO "
			+ "	,CD_SITUACAO		", nativeQuery = true)
	public List<Permissao> getPermissoesNaoAssociadasUsuario(Long idUsuario);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO USUARIO_PERMISSAO (ID_USUARIO, ID_PERMISSAO) "
			+ "SELECT  "
			+ "	?1 "
			+ "	,A.ID_PERMISSAO "
			+ "FROM  "
			+ "	SEG_PERMISSAO AS A "
			+ "	INNER JOIN USUARIO_PERMISSAO AS B ON B.ID_PERMISSAO = A.ID_PERMISSAO "
			+ "WHERE "
			+ "	A.CD_SITUACAO = 1 "
			+ "	AND A.ID_PERMISSAO IN(?2) "
			+ "	AND A.ID_PERMISSAO NOT IN(SELECT ID_PERMISSAO FROM USUARIO_PERMISSAO WHERE ID_USUARIO = ?1 AND ID_PERMISSAO IN(?2))", nativeQuery = true)
	public int incluirPermissaoUsuario(Long idUsuario, List<Long> listaIdPermissao);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE USUARIO_PERMISSAO WHERE ID_USUARIO = ?1 AND ID_PERMISSAO IN(?2);", nativeQuery = true)
	public int removerPermissaoUsuario(Long idUsuario, List<Long> listaIdPermissao);
	
}
