package br.com.sil.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sil.model.Leitura;
import br.com.sil.model.dto.LeituraDto;
import br.com.sil.repository.filter.LeituraFilter;
import br.com.sil.repository.projection.LeituraProjection;
import br.com.sil.repository.projection.LeituraRepasseProjection;
import br.com.sil.resource.interfaces.ILeituraResource;
import br.com.sil.service.LeituraService;

@RestController
@RequestMapping("/leitura")
public class LeituraResource implements ILeituraResource {
	
	@Autowired
	private LeituraService leituraService;

	@Override
	public ResponseEntity<?> incluir(@RequestBody Leitura entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> alterar(Leitura entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> buscarPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@GetMapping("/listarlancamentoleitura")
	public ResponseEntity<?> listarPorTarefa(LeituraFilter filter) {
		List<Leitura> lista = this.leituraService.listarLancamento(filter);
		return new ResponseEntity<List<Leitura>>(lista, HttpStatus.OK);
	}

	@GetMapping("/pesquisar")
	public ResponseEntity<?> pesquisar(LeituraFilter filter, Pageable pageable) {
		Page<Leitura> lista = this.leituraService.pesquisar(filter, pageable);
		return new ResponseEntity<Page<Leitura>>(lista, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> pesquisar(LeituraFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@GetMapping("/leiturapendente")
	public ResponseEntity<?> getLeituraPendente(LeituraFilter filter) {
		List<LeituraProjection> lista = this.leituraService.getLeituraPendente(filter);
		return new ResponseEntity<List<LeituraProjection>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/tarefapendente")
	public ResponseEntity<?> getTarefaPendente(LeituraFilter filter) {
		List<LeituraProjection> lista = this.leituraService.getTarefaPendente(filter);
		return new ResponseEntity<List<LeituraProjection>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/repasse")
	public ResponseEntity<?> getLeituraRepasse(LeituraFilter filter) {
		List<LeituraRepasseProjection> lista = this.leituraService.getLeituraRepasse(filter);
		return new ResponseEntity<List<LeituraRepasseProjection>>(lista, HttpStatus.OK);
	}
	
	@PutMapping("/alterartarefa")
	public ResponseEntity<?> alterarTarefa(@RequestBody LeituraDto entity) {
		int qtdAlterado = this.leituraService.alterarTarefa(entity);
		return new ResponseEntity<Integer>(qtdAlterado, HttpStatus.OK);
	}
	
	@PutMapping("/alterartarefaporendereco")
	public ResponseEntity<?> alterarTarefaPorEndereco(@RequestBody LeituraDto entity) {
		int qtdAlterado = this.leituraService.alterarTarefaPorEndereco(entity);
		return new ResponseEntity<Integer>(qtdAlterado, HttpStatus.OK);
	}

}
