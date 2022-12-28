package br.com.sil.service.interfaces;

import java.io.Serializable;

import br.com.sil.model.GrupoFaturamento;
import br.com.sil.repository.filter.GrupoFaturamentoFilter;
import br.com.sil.util.IGenericService;

public interface IGrupoFaturamentoService extends IGenericService<GrupoFaturamento, GrupoFaturamentoFilter, Serializable> {

}
