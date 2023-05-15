package br.com.sil.resource.interfaces;

import java.io.Serializable;

import br.com.sil.model.Permissao;
import br.com.sil.repository.filter.PermissaoFilter;
import br.com.sil.util.IGenericResource;

public interface IPermissaoResource extends IGenericResource<Permissao, PermissaoFilter, Serializable> {
	
}
