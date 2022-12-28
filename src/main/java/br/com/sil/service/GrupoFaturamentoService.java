package br.com.sil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sil.model.GrupoFaturamento;
import br.com.sil.repository.GrupoFaturamentoRepository;
import br.com.sil.repository.filter.GrupoFaturamentoFilter;
import br.com.sil.service.interfaces.IGrupoFaturamentoService;

@Service
public class GrupoFaturamentoService implements IGrupoFaturamentoService {

	@Autowired
	private GrupoFaturamentoRepository grupoFaturamentoRepository;

	@Override
	public GrupoFaturamento incluir(GrupoFaturamento entity) {
		return this.grupoFaturamentoRepository.save(entity);
	}

	@Override
	public GrupoFaturamento alterar(GrupoFaturamento entity) {
		return this.grupoFaturamentoRepository.save(entity);
	}

	@Override
	public GrupoFaturamento buscarPorId(long id) {
		return this.grupoFaturamentoRepository.findById(id).get();
	}

	@Override
	public List<GrupoFaturamento> pesquisar(GrupoFaturamentoFilter filter) {
		return this.grupoFaturamentoRepository.pesquisar(filter);
	}

	public Optional<GrupoFaturamento> findByCodigo(int codigo) {
		return this.grupoFaturamentoRepository.findByCodigo(codigo);
	}

}
