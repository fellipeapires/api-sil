package br.com.sil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sil.model.GrupoFaturamento;
import br.com.sil.repository.grupofaturamento.GrupoFaturamentoRepositoryQuery;

public interface GrupoFaturamentoRepository extends JpaRepository<GrupoFaturamento, Long>, GrupoFaturamentoRepositoryQuery {
	public Optional<GrupoFaturamento> findByCodigo(int codigo);	
}
