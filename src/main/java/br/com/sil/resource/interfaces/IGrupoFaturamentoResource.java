package br.com.sil.resource.interfaces;

import java.io.Serializable;

import br.com.sil.model.GrupoFaturamento;
import br.com.sil.repository.filter.GrupoFaturamentoFilter;
import br.com.sil.util.IGenericResource;

public interface IGrupoFaturamentoResource extends IGenericResource<GrupoFaturamento, GrupoFaturamentoFilter, Serializable> {

}
