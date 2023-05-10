package br.com.sil.service.interfaces;

import java.io.Serializable;

import br.com.sil.model.Permissao;
import br.com.sil.repository.filter.PermissaoFilter;
import br.com.sil.util.IGenericService;

public interface IPermissaoService extends IGenericService<Permissao, PermissaoFilter, Serializable> {

}
