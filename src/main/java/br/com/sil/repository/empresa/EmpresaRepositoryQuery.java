package br.com.sil.repository.empresa;

import java.util.List;

import br.com.sil.model.Empresa;
import br.com.sil.repository.filter.EmpresaFilter;


public interface EmpresaRepositoryQuery {
	public List<Empresa> pesquisar(EmpresaFilter filter);
}
