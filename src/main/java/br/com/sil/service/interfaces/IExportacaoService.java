package br.com.sil.service.interfaces;

import java.io.Serializable;

import br.com.sil.model.Exportacao;
import br.com.sil.repository.filter.ExportacaoFilter;
import br.com.sil.util.IGenericService;

public interface IExportacaoService extends IGenericService<Exportacao, ExportacaoFilter, Serializable> {

}
