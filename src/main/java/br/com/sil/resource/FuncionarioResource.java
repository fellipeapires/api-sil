package br.com.sil.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sil.model.Usuario;
import br.com.sil.repository.filter.UsuarioFilter;
import br.com.sil.repository.projection.UsuarioMobileProjection;
import br.com.sil.resource.interfaces.IUsuarioResource;
import br.com.sil.service.UsuarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioResource implements IUsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/funcionariosMobile")
	public ResponseEntity<?> getUsuariosMobile() {
		List<UsuarioMobileProjection> lista = this.usuarioService.getUsuariosMobile();
		return new ResponseEntity<List<UsuarioMobileProjection>>(lista, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> incluir(Usuario entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> alterar(Usuario entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> buscarPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> pesquisar(UsuarioFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
