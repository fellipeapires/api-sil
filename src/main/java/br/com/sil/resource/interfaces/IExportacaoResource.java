package br.com.sil.resource.interfaces;

import java.io.Serializable;

import br.com.sil.model.Exportacao;
import br.com.sil.repository.filter.ExportacaoFilter;
import br.com.sil.util.IGenericResource;

public interface IExportacaoResource extends IGenericResource<Exportacao, ExportacaoFilter, Serializable> {

}
