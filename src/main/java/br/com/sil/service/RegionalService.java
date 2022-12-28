package br.com.sil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sil.model.Regional;
import br.com.sil.repository.RegionalRepository;
import br.com.sil.repository.filter.RegionalFilter;
import br.com.sil.service.interfaces.IRegionalService;

@Service
public class RegionalService implements IRegionalService {
	
	@Autowired
	private RegionalRepository regionalRepository; 

	@Override
	public Regional incluir(Regional entity) {
		return this.regionalRepository.save(entity);
	}

	@Override
	public Regional alterar(Regional entity) {
		return this.regionalRepository.save(entity);
	}

	@Override
	public Regional buscarPorId(long id) {
		return this.regionalRepository.findById(id).get();
	}

	@Override
	public List<Regional> pesquisar(RegionalFilter filter) {
		return this.regionalRepository.pesquisar(filter);
	}
	
	public List<Regional> getRegionaisPorUsuario(long idUsuario) {
		return this.regionalRepository.getRegionaisPorUsuario(idUsuario);
	}
}
