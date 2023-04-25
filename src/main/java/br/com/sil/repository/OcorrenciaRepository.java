package br.com.sil.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sil.model.Ocorrencia;
import br.com.sil.repository.ocorrencia.OcorrenciaRepositoryQuery;
import br.com.sil.repository.projection.OcorrenciaProjection;


public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long>, OcorrenciaRepositoryQuery {

	public Optional<Ocorrencia> findByCodigo(Integer codigo);
	
	@Transactional
	@Query(value="SELECT ID_OCORRENCIA AS ID, CD_OCORRENCIA AS CODIGOOCORRENCIA, NM_NOME AS OCORRENCIA, CD_SITUACAO AS SITUACAO, "
			+ "CASE WHEN FL_TIPO_OCORRENCIA = 0 THEN 2 ELSE 1 END AS MEDIA, "
			+ "FL_USO_MOBILE AS USOMOBILE, 0 AS IDUSUARIO, 0 AS QTDEREGISTROS, 0 AS PAGINA, 0 as QTDE "
			+ "FROM MED_OCORRENCIA WHERE CD_SITUACAO = 1 AND FL_USO_MOBILE = 1 ", nativeQuery = true)
	public List<OcorrenciaProjection> getListaMobile();
}
