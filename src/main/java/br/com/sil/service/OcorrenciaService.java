package br.com.sil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sil.model.Ocorrencia;
import br.com.sil.repository.OcorrenciaRepository;
import br.com.sil.repository.filter.OcorrenciaFilter;
import br.com.sil.service.interfaces.IOcorrenciaService;

@Service
public class OcorrenciaService implements IOcorrenciaService {

	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;

	@Override
	public Ocorrencia incluir(Ocorrencia entity) {
		return this.ocorrenciaRepository.save(entity);
	}

	@Override
	public Ocorrencia alterar(Ocorrencia entity) {
		return this.ocorrenciaRepository.save(entity);
	}

	@Override
	public Ocorrencia buscarPorId(long id) {
		return this.ocorrenciaRepository.findById(id).get();
	}

	@Override
	public List<Ocorrencia> pesquisar(OcorrenciaFilter filter) {
		return this.ocorrenciaRepository.pesquisar(filter);
	}
	
	public Optional<Ocorrencia> findByCodigo(int codigo) {
		return this.ocorrenciaRepository.findByCodigo(codigo);
	}

}
