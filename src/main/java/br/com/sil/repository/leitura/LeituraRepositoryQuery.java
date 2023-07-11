package br.com.sil.repository.leitura;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sil.model.Leitura;
import br.com.sil.repository.filter.LeituraFilter;


public interface LeituraRepositoryQuery {
	
	public Page<Leitura> pesquisar(LeituraFilter filter, Pageable pageable);
}
