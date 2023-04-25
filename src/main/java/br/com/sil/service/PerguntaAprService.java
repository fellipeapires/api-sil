package br.com.sil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sil.model.PerguntaApr;
import br.com.sil.repository.PerguntaAprRepository;
import br.com.sil.repository.filter.PerguntaAprFilter;
import br.com.sil.repository.projection.PerguntaAprProjection;
import br.com.sil.service.interfaces.IPerguntaAprService;

@Service
public class PerguntaAprService implements IPerguntaAprService {
	
	@Autowired
	private PerguntaAprRepository perguntaAprRepository;
	
	public List<PerguntaAprProjection> getPerguntasAprMobile(){
		return this.perguntaAprRepository.getPerguntasAprMobile();
	}
	
	@Override
	public PerguntaApr incluir(PerguntaApr entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PerguntaApr alterar(PerguntaApr entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PerguntaApr buscarPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PerguntaApr> pesquisar(PerguntaAprFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
