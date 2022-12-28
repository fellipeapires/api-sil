package br.com.sil.repository.perfilacesso;

import java.util.List;

import br.com.sil.model.PerfilAcesso;
import br.com.sil.repository.filter.PerfilAcessoFilter;


public interface PerfilAcessoRepositoryQuery {
	public List<PerfilAcesso> pesquisar(PerfilAcessoFilter filter);
}
