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

import br.com.sil.model.Regional;
import br.com.sil.repository.filter.RegionalFilter;
import br.com.sil.resource.interfaces.IRegionalResource;
import br.com.sil.service.RegionalService;

@RestController
@RequestMapping("/regional")
public class RegionalResource implements IRegionalResource {

	@Autowired
	RegionalService regionalService;

	@Override
	@PostMapping("/incluir")
	public ResponseEntity<Regional> incluir(@RequestBody Regional entity) {
		Regional regional = this.regionalService.incluir(entity);
		return new ResponseEntity<Regional>(regional, HttpStatus.CREATED);
	}

	@Override
	@PutMapping("/alterar")
	public ResponseEntity<?> alterar(@RequestBody Regional entity) {
		Regional regional = this.regionalService.alterar(entity);
		return new ResponseEntity<Regional>(regional, HttpStatus.OK);
	}

	@Override
	@GetMapping("/buscarporid/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable("id") long id) {
		Regional regional = this.regionalService.buscarPorId(id);
		return regional != null ? new ResponseEntity<Regional>(regional, HttpStatus.OK) : ResponseEntity.notFound().build();
	}

	@Override
	@GetMapping("/pesquisar")
	public ResponseEntity<?> pesquisar(RegionalFilter filter) {
		List<Regional> lista = this.regionalService.pesquisar(filter);
		return new ResponseEntity<List<Regional>>(lista, HttpStatus.OK);
	}

}
