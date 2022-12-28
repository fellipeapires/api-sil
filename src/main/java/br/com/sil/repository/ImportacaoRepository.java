package br.com.sil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sil.model.Importacao;
import br.com.sil.repository.importacao.ImportacaoRepositoryQuery;


public interface ImportacaoRepository extends JpaRepository<Importacao, Long>, ImportacaoRepositoryQuery {

	public Optional<Importacao> findByNome(String nome);	
}
