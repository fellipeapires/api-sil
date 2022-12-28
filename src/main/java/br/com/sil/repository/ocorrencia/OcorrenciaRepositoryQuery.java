package br.com.sil.repository.ocorrencia;

import java.util.List;

import br.com.sil.model.Ocorrencia;
import br.com.sil.repository.filter.OcorrenciaFilter;

public interface OcorrenciaRepositoryQuery {
	public List<Ocorrencia> pesquisar(OcorrenciaFilter filter);
}
