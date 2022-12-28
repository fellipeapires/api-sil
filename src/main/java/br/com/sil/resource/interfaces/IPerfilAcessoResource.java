package br.com.sil.resource.interfaces;

import java.io.Serializable;

import br.com.sil.model.PerfilAcesso;
import br.com.sil.repository.filter.PerfilAcessoFilter;
import br.com.sil.util.IGenericResource;

public interface IPerfilAcessoResource extends IGenericResource<PerfilAcesso, PerfilAcessoFilter, Serializable> {

}
