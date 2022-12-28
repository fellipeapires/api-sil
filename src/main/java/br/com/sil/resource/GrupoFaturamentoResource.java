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

import br.com.sil.model.GrupoFaturamento;
import br.com.sil.repository.filter.GrupoFaturamentoFilter;
import br.com.sil.resource.interfaces.IGrupoFaturamentoResource;
import br.com.sil.service.GrupoFaturamentoService;

@RestController
@RequestMapping("/grupofaturamento")
public class GrupoFaturamentoResource implements IGrupoFaturamentoResource {

	@Autowired
	GrupoFaturamentoService grupoFaturamentoService;

	@Override
	@PostMapping("/incluir")
	public ResponseEntity<GrupoFaturamento> incluir(@RequestBody GrupoFaturamento entity) {
		GrupoFaturamento grupoFaturamento = this.grupoFaturamentoService.incluir(entity);
		return new ResponseEntity<GrupoFaturamento>(grupoFaturamento, HttpStatus.CREATED);
	}

	@Override
	@PutMapping("/alterar")
	public ResponseEntity<?> alterar(@RequestBody GrupoFaturamento entity) {
		GrupoFaturamento grupoFaturamento = this.grupoFaturamentoService.alterar(entity);
		return new ResponseEntity<GrupoFaturamento>(grupoFaturamento, HttpStatus.OK);
	}

	@Override
	@GetMapping("/buscarporid/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable("id") long id) {
		GrupoFaturamento grupoFaturamento = this.grupoFaturamentoService.buscarPorId(id);
		return grupoFaturamento != null ? new ResponseEntity<GrupoFaturamento>(grupoFaturamento, HttpStatus.OK) : ResponseEntity.notFound().build();
	}

	@Override
	@GetMapping("/pesquisar")
	public ResponseEntity<?> pesquisar(GrupoFaturamentoFilter filter) {
		List<GrupoFaturamento> lista = this.grupoFaturamentoService.pesquisar(filter);
		return new ResponseEntity<List<GrupoFaturamento>>(lista, HttpStatus.OK);
	}

}
