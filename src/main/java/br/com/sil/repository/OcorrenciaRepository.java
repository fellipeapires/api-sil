package br.com.sil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sil.model.Ocorrencia;
import br.com.sil.repository.ocorrencia.OcorrenciaRepositoryQuery;


public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long>, OcorrenciaRepositoryQuery {

	public Optional<Ocorrencia> findByCodigo(Integer codigo);
}
