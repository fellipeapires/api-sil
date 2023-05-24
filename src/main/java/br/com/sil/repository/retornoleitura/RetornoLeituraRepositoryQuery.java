package br.com.sil.repository.retornoleitura;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sil.model.RetornoLeitura;
import br.com.sil.repository.filter.RetornoLeituraFilter;


public interface RetornoLeituraRepositoryQuery {
	
	public Page<RetornoLeitura> pesquisar(RetornoLeituraFilter filter, Pageable pageable);
}
