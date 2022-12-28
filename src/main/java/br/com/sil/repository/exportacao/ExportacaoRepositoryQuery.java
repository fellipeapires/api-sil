package br.com.sil.repository.exportacao;

import java.util.List;

import br.com.sil.model.Exportacao;
import br.com.sil.repository.filter.ExportacaoFilter;


public interface ExportacaoRepositoryQuery {
	public List<Exportacao> pesquisar(ExportacaoFilter filter);

}
