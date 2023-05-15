package br.com.sil.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sil.model.Permissao;
import br.com.sil.model.dto.PermissaoDto;
import br.com.sil.repository.filter.PermissaoFilter;
import br.com.sil.resource.interfaces.IPermissaoResource;
import br.com.sil.service.PermissaoService;

@RestController
@RequestMapping("/permissao")
public class PermissaoResource implements IPermissaoResource {
	
	@Autowired
	private PermissaoService permissaoService;

	@Override
	public ResponseEntity<?> incluir(Permissao entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> alterar(Permissao entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> buscarPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> pesquisar(PermissaoFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@GetMapping("/listaassociadausuario")
	public ResponseEntity<?> getPermissoesAssociadasUsuario(PermissaoFilter filter) {
		List<Permissao> lista = this.permissaoService.getPermissoesAssociadasUsuario(filter);
		return new ResponseEntity<List<Permissao>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/listanaoassociadausuario")
	public ResponseEntity<?> getPermissoesNaoAssociadasUsuario(PermissaoFilter filter) {
		List<Permissao> lista = this.permissaoService.getPermissoesNaoAssociadasUsuario(filter);
		return new ResponseEntity<List<Permissao>>(lista, HttpStatus.OK);
	}
	
	@PostMapping("/incluirusuario")
	public ResponseEntity<?> incluirPermissaoUsuario(@RequestBody PermissaoDto entity) {
		int qtd = this.permissaoService.incluirPermissaoUsuario(entity);
		return new ResponseEntity<Integer>(qtd, HttpStatus.OK);
	}
	
	@DeleteMapping("/removerusuario")
	public ResponseEntity<?> removerPermissaoUsuario(@RequestBody PermissaoDto entity) {
		Integer qtdDeletado = this.permissaoService.removerPermissaoUsuario(entity);
		return new ResponseEntity<>(qtdDeletado, HttpStatus.OK);
	}
	
	
}
