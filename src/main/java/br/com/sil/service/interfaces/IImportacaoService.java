package br.com.sil.service.interfaces;

import java.io.Serializable;

import br.com.sil.model.Importacao;
import br.com.sil.repository.filter.ImportacaoFilter;
import br.com.sil.util.IGenericService;

public interface IImportacaoService extends IGenericService<Importacao, ImportacaoFilter, Serializable> {

}
