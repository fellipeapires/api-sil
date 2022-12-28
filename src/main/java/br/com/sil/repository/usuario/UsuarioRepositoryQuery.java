package br.com.sil.repository.usuario;

import java.util.List;

import br.com.sil.model.Usuario;
import br.com.sil.repository.filter.UsuarioFilter;

public interface UsuarioRepositoryQuery {
	public List<Usuario> pesquisar(UsuarioFilter filter);
}
