package br.com.sil.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sil.model.RetornoLeitura;
import br.com.sil.model.dto.RetornoLeituraDto;
import br.com.sil.model.dto.RetornoLeituraMobileDto;
import br.com.sil.repository.filter.RetornoLeituraFilter;
import br.com.sil.repository.projection.AcompanhamentoDetailProjection;
import br.com.sil.repository.projection.AcompanhamentoProjection;
import br.com.sil.repository.projection.AcompanhamentoTotalProjection;
import br.com.sil.repository.projection.MapaProjection;
import br.com.sil.repository.projection.RetornoLeituraProjection;
import br.com.sil.resource.interfaces.IRetornoLeituraResource;
import br.com.sil.service.RetornoLeituraService;

@RestController
@RequestMapping("/retornoleitura")
public class RetornoLeituraResource implements IRetornoLeituraResource {

	@Autowired
	RetornoLeituraService retornoLeituraService;
	
	@Override
	public ResponseEntity<?> incluir(RetornoLeitura entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@PostMapping("/lancarleituramobile")
	public ResponseEntity<?> lancarLeituraMobile(@RequestBody List<RetornoLeituraMobileDto> entity) {
		List<Long> listaId = this.retornoLeituraService.lancarLeituraMobile(entity);
		return new ResponseEntity<List<Long>>(listaId, HttpStatus.CREATED);
	}
	
	@PostMapping("/lancarleitura")
	public ResponseEntity<RetornoLeitura> lancarLeitura(@RequestBody RetornoLeituraDto entity) {
		RetornoLeitura retornoLeitura = this.retornoLeituraService.lancarLeitura(entity);
		return new ResponseEntity<RetornoLeitura>(retornoLeitura, HttpStatus.CREATED);
	}
	
	@PostMapping("/lancarrepasse")
	public ResponseEntity<RetornoLeitura> lancarRepasse(@RequestBody RetornoLeituraDto entity) {
		RetornoLeitura retornoLeitura = this.retornoLeituraService.lancarRepasse(entity);
		return new ResponseEntity<RetornoLeitura>(retornoLeitura, HttpStatus.CREATED);
	}
	
	@PostMapping("/alterardadosleitura")
	public ResponseEntity<RetornoLeitura> alterarDadosLeitura(@RequestBody RetornoLeituraDto entity) {
		RetornoLeitura retornoLeitura = this.retornoLeituraService.alterarDadosLeitura(entity);
		return new ResponseEntity<RetornoLeitura>(retornoLeitura, HttpStatus.CREATED);
	}

	@Override
	@PutMapping("/alterar")
	public ResponseEntity<?> alterar(@RequestBody RetornoLeitura entity) {
		RetornoLeitura retornoLeitura = this.retornoLeituraService.alterar(entity);
		return new ResponseEntity<RetornoLeitura>(retornoLeitura, HttpStatus.OK);
	}

	@Override
	@GetMapping("/buscarporid/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable("id") long id) {
		RetornoLeitura retornoLeitura = this.retornoLeituraService.buscarPorId(id);
		return retornoLeitura != null ? new ResponseEntity<RetornoLeitura>(retornoLeitura, HttpStatus.OK) : ResponseEntity.notFound().build();
	}

	@GetMapping("/pesquisar")
	public ResponseEntity<?> pesquisar(RetornoLeituraFilter filter, Pageable pageable) {
		Page<RetornoLeitura> lista = this.retornoLeituraService.pesquisar(filter, pageable);
		return new ResponseEntity<Page<RetornoLeitura>>(lista, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> pesquisar(RetornoLeituraFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@GetMapping("/acompanhamento")
	public ResponseEntity<?> getAcompanhamento(RetornoLeituraFilter filter) {
		List<AcompanhamentoProjection> lista = this.retornoLeituraService.getAcompanhamento(filter);
		return new ResponseEntity<List<AcompanhamentoProjection>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/acompanhamentototal")
	public ResponseEntity<?> getAcompanhamentoTotal(RetornoLeituraFilter filter) {
		AcompanhamentoTotalProjection total = this.retornoLeituraService.getAcompanhamentoTotal(filter);
		return new ResponseEntity<AcompanhamentoTotalProjection>(total, HttpStatus.OK);
	}
	
	@GetMapping("/acompanhamentodetail")
	public ResponseEntity<?> getAcompanhamentoDetail(RetornoLeituraFilter filter) {
		List<AcompanhamentoDetailProjection> lista = this.retornoLeituraService.getAcompanhamentoDetail(filter);
		return new ResponseEntity<List<AcompanhamentoDetailProjection>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/mapa")
	public ResponseEntity<?> getMapa(RetornoLeituraFilter filter) {
		List<MapaProjection> lista = this.retornoLeituraService.getMapa(filter);
		return new ResponseEntity<List<MapaProjection>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/mapaporretornoleitura/{id}")
	public ResponseEntity<?> getMapaPorRetornoLeitura(@PathVariable("id") long id) {
		List<MapaProjection> lista = this.retornoLeituraService.getMapaPorRetornoLeitura(id);
		return new ResponseEntity<List<MapaProjection>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/maparoteiro")
	public ResponseEntity<?> getMapaRoteiro(RetornoLeituraFilter filter) {
		List<MapaProjection> lista = this.retornoLeituraService.getMapaRoteiro(filter);
		return new ResponseEntity<List<MapaProjection>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/detalhamento")
	public ResponseEntity<?> getRetornoLeituraDetail(RetornoLeituraFilter filter) {
		List<RetornoLeituraProjection> lista = this.retornoLeituraService.getRetornoLeituraDetail(filter);
		return new ResponseEntity<List<RetornoLeituraProjection>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/ultimostresmeses")
	public ResponseEntity<?> getUltimosTresMeses(RetornoLeituraFilter filter) {
		List<RetornoLeituraProjection> lista = this.retornoLeituraService.getUltimosTresMeses(filter);
		return new ResponseEntity<List<RetornoLeituraProjection>>(lista, HttpStatus.OK);
	}
	
	@PostMapping("/revisao")
	public ResponseEntity<?> setRevisao(@RequestBody RetornoLeituraDto entity) {
		RetornoLeitura retornoLeitura = this.retornoLeituraService.setRevisao(entity);
		return new ResponseEntity<RetornoLeitura>(retornoLeitura, HttpStatus.OK);
	}

}
