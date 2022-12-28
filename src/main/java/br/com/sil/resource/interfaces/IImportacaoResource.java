package br.com.sil.resource.interfaces;

import java.io.Serializable;

import br.com.sil.model.Importacao;
import br.com.sil.repository.filter.ImportacaoFilter;
import br.com.sil.util.IGenericResource;

public interface IImportacaoResource extends IGenericResource<Importacao, ImportacaoFilter, Serializable> {

}
