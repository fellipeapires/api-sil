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

import br.com.sil.model.PerfilAcesso;
import br.com.sil.repository.filter.PerfilAcessoFilter;
import br.com.sil.resource.interfaces.IPerfilAcessoResource;
import br.com.sil.service.PerfilAcessoService;

@RestController
@RequestMapping("/perfilacesso")
public class PerfilAcessoResource implements IPerfilAcessoResource {
	
	@Autowired
	PerfilAcessoService perfilAcessoService;

	@Override
	@PostMapping("/incluir")
	public ResponseEntity<PerfilAcesso> incluir(@RequestBody PerfilAcesso entity) {
		PerfilAcesso perfilAcesso = this.perfilAcessoService.incluir(entity);
		return new ResponseEntity<PerfilAcesso>(perfilAcesso, HttpStatus.CREATED);
	}

	@Override
	@PutMapping("/alterar")
	public ResponseEntity<?> alterar(@RequestBody PerfilAcesso entity) {
		PerfilAcesso perfilAcesso = this.perfilAcessoService.alterar(entity);
		return new ResponseEntity<PerfilAcesso>(perfilAcesso, HttpStatus.OK);
	}

	@Override
	@GetMapping("/buscarporid/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable("id") long id) {
		PerfilAcesso perfilAcesso = this.perfilAcessoService.buscarPorId(id);
		return perfilAcesso != null ? new ResponseEntity<PerfilAcesso>(perfilAcesso, HttpStatus.OK) : ResponseEntity.notFound().build();
	}

	@Override
	@GetMapping("/pesquisar")
	public ResponseEntity<?> pesquisar(PerfilAcessoFilter filter) {
		List<PerfilAcesso> lista = this.perfilAcessoService.pesquisar(filter);
		return new ResponseEntity<List<PerfilAcesso>>(lista, HttpStatus.OK);
	}

}
