package br.com.sil.repository.retornofoto;

import java.util.List;

import br.com.sil.model.RetornoFoto;
import br.com.sil.repository.filter.RetornoFotoFilter;

public interface RetornoFotoRepositoryQuery {
	public List<RetornoFoto> pesquisar(RetornoFotoFilter filter);
}
