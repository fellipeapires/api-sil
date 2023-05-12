package br.com.sil.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.sil.model.Usuario;
import br.com.sil.repository.projection.AcessoSistemaUsuarioProjection;
import br.com.sil.repository.projection.UsuarioMobileProjection;
import br.com.sil.repository.usuario.UsuarioRepositoryQuery;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery {
	
	public Optional<Usuario> findByLogin(String login);
		
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO LOG_ACESSO (ID_USUARIO, NM_BROWSER, NR_IP, DT_ACESSO) VALUES (?1, ?2, ?3, ?4);", nativeQuery = true)
	public int resgistrarAcesso(Long idUsuario, String browser, String ip, LocalDateTime dataAcesso);
	
	@Transactional
	@Query(value="SELECT "
			+ "	A.ID_USUARIO											AS ID, 0 AS IDUSUARIO, "
			+ "	0 														AS QTDEREGISTROS, "
			+ " 0														AS PAGINA, "
			+ "	NULL 													AS REGIONAL, "
			+ "	A.NM_NOME 												AS NOME, "
			+ "	A.NM_LOGIN 												AS LOGIN, "
			+ "	A.NM_SENHA 												AS SENHA, "
			+ "	A.NR_MATRICULA 											AS MATRICULA, "
			+ "	CASE WHEN C.ID_REGIONAL = 1 THEN '(11)' ELSE '(12)' END	AS TELEFONE, "
			+ "	'1' 													AS IDDEVICE, "
			+ "	A.CD_SITUACAO 											AS SITUACAO, "
			+ "	1														AS BASEDADOS, "
			+ " 0														AS CARGO "
			+ "FROM SEG_USUARIO AS A "
			+ "	INNER JOIN SEG_PERFIL_ACESSO AS B ON B.ID_PERFIL_ACESSO = A.ID_PERFIL_ACESSO "
			+ "	INNER JOIN SEG_USUARIO_REGIONAL AS C ON C.ID_USUARIO = A.ID_USUARIO "
			+ "WHERE A.CD_SITUACAO = 1 "
			+ "	AND B.CD_SITUACAO = 1 "
			+ "	AND B.NM_NOME = 'MOBILE' ", nativeQuery = true)
	public List<UsuarioMobileProjection> getUsuariosMobile();
	
	@Transactional
	@Query(value="SELECT "
			+ "	 A.NM_NOME									AS USUARIO "
			+ "	,B.NM_NOME									AS PERFILACESSO "
			+ "	,CAST(E.DT_ACESSO AS DATE)					AS DATAACESSO "
			+ "	,CONVERT(CHAR(8), MIN(E.DT_ACESSO), 108)	AS PRIMEIROACESSO "
			+ "	,CONVERT(CHAR(8), MAX(E.DT_ACESSO), 108) 	AS ULTIMOACESSO "
			+ "	,E.NR_IP									AS IPACESSO "
			+ "	,MAX(E.NM_BROWSER)							AS ACESSOULTIMOBROWSER "
			+ "	,COUNT(*) 									AS QTDACESSO "
			+ "FROM "
			+ "	SEG_USUARIO AS A "
			+ "	INNER JOIN SEG_PERFIL_ACESSO AS B ON B.ID_PERFIL_ACESSO = A.ID_PERFIL_ACESSO "
			+ "	INNER JOIN SEG_USUARIO_REGIONAL AS C ON C.ID_USUARIO = A.ID_USUARIO "
			+ "	INNER JOIN CAD_REGIONAL AS D ON D.ID_REGIONAL = C.ID_REGIONAL "
			+ "	INNER JOIN LOG_ACESSO AS E ON E.ID_USUARIO = A.ID_USUARIO "
			+ "WHERE "
			+ "	A.ID_USUARIO = ISNULL((CASE WHEN ?1 > 0 THEN ?1 ELSE NULL END), A.ID_USUARIO) "
			+ " AND CAST(E.DT_ACESSO AS DATE) BETWEEN ?2 AND ?3 "
			+ "GROUP BY "
			+ "	 A.NM_NOME "
			+ "	,B.NM_NOME "
			+ "	,CAST(E.DT_ACESSO AS DATE) "
			+ "	,E.NR_IP "
			+ "ORDER BY "
			+ "	 CAST(E.DT_ACESSO AS DATE) DESC", nativeQuery = true)
	public List<AcessoSistemaUsuarioProjection> listarAcessoSistema(Long idUsuario, LocalDate dataInicio, LocalDate dataFim);
	
}
