package br.com.sil.repository.retornoleitura;

import java.util.List;

import br.com.sil.model.RetornoLeitura;
import br.com.sil.repository.filter.RetornoLeituraFilter;


public interface RetornoLeituraRepositoryQuery {
	
	public List<RetornoLeitura> pesquisar(RetornoLeituraFilter filter);
}
