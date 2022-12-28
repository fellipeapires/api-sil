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

import br.com.sil.model.Empresa;
import br.com.sil.repository.filter.EmpresaFilter;
import br.com.sil.resource.interfaces.IEmpresaResource;
import br.com.sil.service.EmpresaService;

@RestController
@RequestMapping("/empresa")
public class EmpresaResource implements IEmpresaResource {

	@Autowired
	EmpresaService empresaService;

	@Override
	@PostMapping("/incluir")
	public ResponseEntity<Empresa> incluir(@RequestBody Empresa entity) {
		Empresa empresa = this.empresaService.incluir(entity);
		return new ResponseEntity<Empresa>(empresa, HttpStatus.CREATED);
	}

	@Override
	@PutMapping("/alterar")
	public ResponseEntity<?> alterar(@RequestBody Empresa entity) {
		Empresa empresa = this.empresaService.alterar(entity);
		return new ResponseEntity<Empresa>(empresa, HttpStatus.OK);
	}

	@Override
	@GetMapping("/buscarporid/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable("id") long id) {
		Empresa empresa = this.empresaService.buscarPorId(id);
		return empresa != null ? new ResponseEntity<Empresa>(empresa, HttpStatus.OK) : ResponseEntity.notFound().build();
	}

	@Override
	@GetMapping("/pesquisar")
	public ResponseEntity<?> pesquisar(EmpresaFilter filter) {
		List<Empresa> lista = this.empresaService.pesquisar(filter);
		return new ResponseEntity<List<Empresa>>(lista, HttpStatus.OK);
	}

}
