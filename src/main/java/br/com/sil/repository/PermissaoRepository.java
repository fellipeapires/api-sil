package br.com.sil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sil.model.Permissao;
import br.com.sil.repository.permissao.PermissaoRepositoryQuery;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>, PermissaoRepositoryQuery {
	public Optional<Permissao> findByNome(String nome);
}
