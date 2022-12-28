package br.com.sil.repository.importacao;

import java.util.List;

import br.com.sil.model.Importacao;
import br.com.sil.repository.filter.ImportacaoFilter;

public interface ImportacaoRepositoryQuery {
	public List<Importacao> pesquisar(ImportacaoFilter filter);
}
