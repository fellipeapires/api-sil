package br.com.sil.resource.interfaces;

import java.io.Serializable;

import br.com.sil.model.Usuario;
import br.com.sil.repository.filter.UsuarioFilter;
import br.com.sil.util.IGenericResource;

public interface IUsuarioResource extends IGenericResource<Usuario, UsuarioFilter, Serializable> {

}
