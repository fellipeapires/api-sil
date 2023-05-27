package br.com.sil.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sil.model.Regional;
import br.com.sil.repository.regional.RegionalRepositoryQuery;


public interface RegionalRepository extends JpaRepository<Regional, Long>, RegionalRepositoryQuery {
	
	@Transactional
	@Query(value="SELECT "
			+ "	 A.ID_REGIONAL"
			+ "	,A.ID_EMPRESA"
			+ "	,A.NM_NOME"
			+ "	,A.NM_CIDADE"
			+ "	,A.CD_SITUACAO "
			+ "FROM "
			+ "	CAD_REGIONAL A INNER JOIN SEG_USUARIO_REGIONAL B ON B.ID_REGIONAL = A.ID_REGIONAL "
			+ "WHERE "
			+ "	B.ID_USUARIO = ?1", nativeQuery = true)
	public List<Regional> getRegionaisPorUsuario(long idUsuario);

}
