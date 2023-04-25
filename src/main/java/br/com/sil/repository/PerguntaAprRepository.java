package br.com.sil.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sil.model.PerguntaApr;
import br.com.sil.repository.perguntaapr.PerguntaAprRepositoryQuery;
import br.com.sil.repository.projection.PerguntaAprProjection;

public interface PerguntaAprRepository extends JpaRepository<PerguntaApr, Long>, PerguntaAprRepositoryQuery {
	
	@Transactional
	@Query(value="SELECT ID_PERGUNTA_APR AS ID, NR_PERGUNTA_APR AS NUMEROPERGUNTA, DS_PERGUNTA_APR AS PERGUNTA, CD_SITUACAO AS SITUACAO, 0 AS IDUSUARIO, 0 AS QTDEREGISTROS, 0 AS PAGINA "
			+ "FROM CAD_PERGUNTA_APR WHERE CD_SITUACAO = 1 ", nativeQuery = true)
	public List<PerguntaAprProjection> getPerguntasAprMobile();

}