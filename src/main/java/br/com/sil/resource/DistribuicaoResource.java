package br.com.sil.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sil.model.Distribuicao;
import br.com.sil.model.dto.DistribuicaoDto;
import br.com.sil.repository.filter.DistribuicaoFilter;
import br.com.sil.repository.projection.CargaMobileProjection;
import br.com.sil.repository.projection.DistribuicaoProjection;
import br.com.sil.repository.projection.DistribuidoDetailProjection;
import br.com.sil.repository.projection.DistribuidoProjection;
import br.com.sil.resource.interfaces.IDistribuicaoResource;
import br.com.sil.service.DistribuicaoService;

@RestController
@RequestMapping("/distribuicao")
public class DistribuicaoResource implements IDistribuicaoResource {
	
	@Autowired
	private DistribuicaoService distribuicaoService;
	
	@Override
	public ResponseEntity<?> incluir(Distribuicao entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@PostMapping("/incluir")
	public ResponseEntity<?> incluir(@RequestBody List<DistribuicaoDto> lista) {
		int qtd = this.distribuicaoService.incluir(lista);
		return new ResponseEntity<Integer>(qtd, HttpStatus.OK);
	}
	
	@PostMapping("/duplicar")
	public ResponseEntity<?> duplicar(@RequestBody DistribuicaoDto entity) {
		Integer qtdIncluido = this.distribuicaoService.duplicar(entity);
		return new ResponseEntity<Integer>(qtdIncluido, HttpStatus.OK);
	}
	
	@DeleteMapping("/desassociar/{dataReferencia}/{idRegional}/{grupoFaturamento}/{tarefa}/{idUsuario}")
	public ResponseEntity<?> desassociar(@PathVariable("dataReferencia") String dataReferencia, @PathVariable("idRegional") long idRegional, @PathVariable("grupoFaturamento") int grupoFaturamento, @PathVariable("tarefa") String tarefa, @PathVariable("idUsuario") long idUsuario) {
		Integer qtdDeletado = this.distribuicaoService.desassociar(dataReferencia, idRegional, grupoFaturamento, tarefa, idUsuario);
		return new ResponseEntity<Integer>(qtdDeletado, HttpStatus.OK);
	}
	
	@DeleteMapping("/desassociarindividual/{idDistribuicao}")
	public ResponseEntity<?> desassociar(@PathVariable("idDistribuicao") long idDistribuicao) {
		Integer qtdDeletado = this.distribuicaoService.desassociarIndividual(idDistribuicao);
		return new ResponseEntity<Integer>(qtdDeletado, HttpStatus.OK);
	}

	@Override
	@PutMapping("/alterar")
	public ResponseEntity<?> alterar(Distribuicao entity) {
		Distribuicao distribuicao = this.distribuicaoService.alterar(entity);
		return new ResponseEntity<Distribuicao>(distribuicao, HttpStatus.OK);
	}

	@Override
	@GetMapping("/buscarporid/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable("id") long id) {
		Distribuicao distribuicao = this.distribuicaoService.buscarPorId(id);
		return new ResponseEntity<Distribuicao>(distribuicao, HttpStatus.OK);
	}

	@Override
	@GetMapping("/pesquisar")
	public ResponseEntity<?> pesquisar(DistribuicaoFilter filter) {
		List<Distribuicao> lista = this.distribuicaoService.pesquisar(filter);
		return new ResponseEntity<List<Distribuicao>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/pendente")
	public ResponseEntity<?> getPendente(DistribuicaoFilter filter) {
		List<DistribuicaoProjection> lista = this.distribuicaoService.getPendente(filter);
		return new ResponseEntity<List<DistribuicaoProjection>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/distribuido")
	public ResponseEntity<?> getDistribuido(DistribuicaoFilter filter) {
		List<DistribuidoProjection> lista = this.distribuicaoService.getDistribuido(filter);
		return new ResponseEntity<List<DistribuidoProjection>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/distribuidodetail")
	public ResponseEntity<?> getDistribuidoDetail(DistribuicaoFilter filter) {
		List<DistribuidoDetailProjection> lista = this.distribuicaoService.getDistribuidoDetail(filter);
		return new ResponseEntity<List<DistribuidoDetailProjection>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/listarcarga")
	public ResponseEntity<?> listarCarga(DistribuicaoFilter filter) {
		List<DistribuidoProjection> lista = this.distribuicaoService.listarCarga(filter);
		return new ResponseEntity<List<DistribuidoProjection>>(lista, HttpStatus.OK);
	}
	
	@PutMapping("/liberarcarga")
	public ResponseEntity<?> liberarCarga(@RequestBody DistribuicaoDto entity) {
		int qtd = this.distribuicaoService.liberarCarga(entity);
		return new ResponseEntity<Integer>(qtd, HttpStatus.OK);
	}
	
	@GetMapping("/desassociadomobile/{idUsuario}")
	public ResponseEntity<?> getDesassociadoMobile(@PathVariable("idUsuario") long idUsuario) {
		List<Long> lista = this.distribuicaoService.getDesassociadoMobile(idUsuario);
		return new ResponseEntity<List<Long>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/cargamobile/{idUsuario}")
	public ResponseEntity<?> getCargaMobile(@PathVariable("idUsuario") long idUsuario) {
		List<CargaMobileProjection> lista = this.distribuicaoService.getCargaMobile(idUsuario);
		return new ResponseEntity<List<CargaMobileProjection>>(lista, HttpStatus.OK);
	}
	
	@PutMapping("/alterarassociadomobile")
	public ResponseEntity<?> alterarAssociadoMobile(@RequestBody DistribuicaoDto entity) {
		this.distribuicaoService.alterarAssociadoMobile(entity);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@PostMapping("/zerardesassociadomobile")
	public ResponseEntity<?> zerarDesassociadoMobile(@RequestBody DistribuicaoDto entity) {
		this.distribuicaoService.zerarDesassociadoMobile(entity);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
