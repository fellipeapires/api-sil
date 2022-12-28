package br.com.sil.service.interfaces;

import java.io.Serializable;

import br.com.sil.model.Usuario;
import br.com.sil.repository.filter.UsuarioFilter;
import br.com.sil.util.IGenericService;

public interface IUsuarioService extends IGenericService<Usuario, UsuarioFilter, Serializable> {
	
}
