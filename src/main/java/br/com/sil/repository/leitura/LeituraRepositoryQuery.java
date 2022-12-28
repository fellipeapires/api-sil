package br.com.sil.repository.leitura;

import java.util.List;

import br.com.sil.model.Leitura;
import br.com.sil.repository.filter.LeituraFilter;


public interface LeituraRepositoryQuery {

	public List<Leitura> pesquisar(LeituraFilter filter);
}
