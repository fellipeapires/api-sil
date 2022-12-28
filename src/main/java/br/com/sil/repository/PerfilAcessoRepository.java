package br.com.sil.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sil.model.PerfilAcesso;
import br.com.sil.repository.perfilacesso.PerfilAcessoRepositoryQuery;


public interface PerfilAcessoRepository extends JpaRepository<PerfilAcesso, Long>, PerfilAcessoRepositoryQuery {
	
	public Optional<PerfilAcesso> findById(long id);
	
	@Transactional
	@Query(value="SELECT A.ID_PERFIL_ACESSO, A.NM_NOME, A.CD_SITUACAO "
			+ "FROM SEG_PERFIL_ACESSO A INNER JOIN SEG_USUARIO B ON B.ID_PERFIL_ACESSO = A.ID_PERFIL_ACESSO "
			+ "WHERE B.ID_USUARIO = ?1", nativeQuery = true)
	public List<PerfilAcesso> getPerfilPorUsuario(long idUsuario);

}
