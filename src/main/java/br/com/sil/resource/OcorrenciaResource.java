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

import br.com.sil.model.Ocorrencia;
import br.com.sil.repository.filter.OcorrenciaFilter;
import br.com.sil.resource.interfaces.IOcorrenciaResource;
import br.com.sil.service.OcorrenciaService;

@RestController
@RequestMapping("/ocorrencia")
public class OcorrenciaResource implements IOcorrenciaResource {
	
	@Autowired
	OcorrenciaService ocorrenciaService;

	@Override
	@PostMapping("/incluir")
	public ResponseEntity<Ocorrencia> incluir(@RequestBody Ocorrencia entity) {
		Ocorrencia ocorrencia = this.ocorrenciaService.incluir(entity);
		return new ResponseEntity<Ocorrencia>(ocorrencia, HttpStatus.CREATED);
	}

	@Override
	@PutMapping("/alterar")
	public ResponseEntity<?> alterar(@RequestBody Ocorrencia entity) {
		Ocorrencia ocorrencia = this.ocorrenciaService.alterar(entity);
		return new ResponseEntity<Ocorrencia>(ocorrencia, HttpStatus.OK);
	}

	@Override
	@GetMapping("/buscarporid/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable("id") long id) {
		Ocorrencia ocorrencia = this.ocorrenciaService.buscarPorId(id);
		return ocorrencia != null ? new ResponseEntity<Ocorrencia>(ocorrencia, HttpStatus.OK) : ResponseEntity.notFound().build();
	}

	@Override
	@GetMapping("/pesquisar")
	public ResponseEntity<?> pesquisar(OcorrenciaFilter filter) {
		List<Ocorrencia> lista = this.ocorrenciaService.pesquisar(filter);
		return new ResponseEntity<List<Ocorrencia>>(lista, HttpStatus.OK);
	}
}
