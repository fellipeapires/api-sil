package br.com.sil.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sil.model.Usuario;
import br.com.sil.model.dto.AcessoDto;
import br.com.sil.repository.filter.UsuarioFilter;
import br.com.sil.repository.projection.AcessoSistemaUsuarioProjection;
import br.com.sil.resource.interfaces.IUsuarioResource;
import br.com.sil.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource implements IUsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	@PostMapping("/incluir")
	public ResponseEntity<Usuario> incluir(@RequestBody Usuario entity) {
		Usuario usuarioSalva = usuarioService.incluir(entity);
		return new ResponseEntity<Usuario>(usuarioSalva, HttpStatus.CREATED);
	}

	@Override
	@PutMapping("/alterar")
	public ResponseEntity<?> alterar(@RequestBody Usuario entity) {
		Usuario usuario = this.usuarioService.alterar(entity);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@Override
	@GetMapping("/buscarporid/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable("id") long id) {
		Usuario usuario = this.usuarioService.buscarPorId(id);
		return usuario != null ? new ResponseEntity<Usuario>(usuario, HttpStatus.OK)
				: ResponseEntity.notFound().build();
	}

	@Override
	@GetMapping("/pesquisar")
	public ResponseEntity<?> pesquisar(UsuarioFilter filter) {
		List<Usuario> lista = this.usuarioService.pesquisar(filter);
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
	}

	@GetMapping("/resetpassword/{id}")
	public ResponseEntity<?> resetPassword(@PathVariable("id") long id) {
		Usuario usuario = this.usuarioService.resetPassword(id);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@PutMapping("/alterarsenha")
	public ResponseEntity<?> alterarSenha(@RequestBody Usuario entity) {
		Usuario usuario = this.usuarioService.alterarSenha(entity);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@PostMapping("/registraracesso")
	public ResponseEntity<?> registrarAcesso(@RequestBody AcessoDto entity) {
		Integer acesso = this.usuarioService.registrarAcesso(entity);
		return new ResponseEntity<Integer>(acesso, HttpStatus.OK);
	}

	@GetMapping("/listaracessosistema")
	public ResponseEntity<?> listarAcessoSistema(UsuarioFilter filter) {
		List<AcessoSistemaUsuarioProjection> lista = this.usuarioService.listarAcessoSistema(filter);
		return new ResponseEntity<List<AcessoSistemaUsuarioProjection>>(lista, HttpStatus.OK);
	}

}
