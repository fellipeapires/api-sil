package br.com.sil.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sil.model.PerguntaApr;
import br.com.sil.repository.filter.PerguntaAprFilter;
import br.com.sil.repository.projection.PerguntaAprProjection;
import br.com.sil.resource.interfaces.IPerguntaAprResource;
import br.com.sil.service.PerguntaAprService;

@RestController
@RequestMapping("/perguntaApr")
public class PerguntaAprResource implements IPerguntaAprResource {
	@Autowired
	private PerguntaAprService perguntaAprService;
	
	@GetMapping("/mobile")
	public ResponseEntity<?> getPerguntasAprMobile() {
		List<PerguntaAprProjection> lista = this.perguntaAprService.getPerguntasAprMobile();
		return new ResponseEntity<List<PerguntaAprProjection>>(lista, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> incluir(PerguntaApr entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> alterar(PerguntaApr entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> buscarPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> pesquisar(PerguntaAprFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
