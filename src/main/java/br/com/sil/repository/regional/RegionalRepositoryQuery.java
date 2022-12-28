package br.com.sil.repository.regional;

import java.util.List;

import br.com.sil.model.Regional;
import br.com.sil.repository.filter.RegionalFilter;


public interface RegionalRepositoryQuery {
	public List<Regional> pesquisar(RegionalFilter filter);
}
