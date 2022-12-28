package br.com.sil.repository.distribuicao;

import java.util.List;

import br.com.sil.model.Distribuicao;
import br.com.sil.repository.filter.DistribuicaoFilter;

public interface DistribuicaoRepositoryQuery {
	public List<Distribuicao> pesquisar(DistribuicaoFilter filter);
}
