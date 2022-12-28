package br.com.sil.repository.grupofaturamento;

import java.util.List;

import br.com.sil.model.GrupoFaturamento;
import br.com.sil.repository.filter.GrupoFaturamentoFilter;


public interface GrupoFaturamentoRepositoryQuery {
	public List<GrupoFaturamento> pesquisar(GrupoFaturamentoFilter filter);
}
