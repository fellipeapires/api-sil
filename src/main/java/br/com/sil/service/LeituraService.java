package br.com.sil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sil.model.Leitura;
import br.com.sil.repository.LeituraRepository;
import br.com.sil.repository.filter.LeituraFilter;
import br.com.sil.repository.projection.LeituraProjection;
import br.com.sil.repository.projection.LeituraRepasseProjection;
import br.com.sil.service.interfaces.ILeituraService;

@Service
public class LeituraService implements ILeituraService {

	@Autowired
	private LeituraRepository leituraRepository;

	@Override
	public Leitura incluir(Leitura entity) {
		return this.leituraRepository.save(entity);
	}

	@Override
	public Leitura alterar(Leitura entity) {
		return this.leituraRepository.save(entity);
	}

	@Override
	public Leitura buscarPorId(long id) {
		return this.leituraRepository.findById(id).get();
	}

	@Override
	public List<Leitura> pesquisar(LeituraFilter filter) {
		return this.leituraRepository.pesquisar(filter);
	}
	
	public List<LeituraProjection> getLeituraPendente(LeituraFilter filter) {
		return this.leituraRepository.getLeituraPendente(filter.getIdRegional(), filter.getDataReferencia(), filter.getGrupoFaturamento(), filter.getTarefa());
	}
	
	public List<LeituraProjection> getTarefaPendente(LeituraFilter filter) {
		return this.leituraRepository.getTarefaPendente(filter.getIdRegional(), filter.getDataReferencia(), filter.getGrupoFaturamento());
	}
	
	public List<LeituraRepasseProjection> getLeituraRepasse(LeituraFilter filter) {
		if (filter.getIsFoto()) {
			return this.leituraRepository.getLeituraRepasseComFoto(filter.getIdRegional(), filter.getDataReferencia(), filter.getGrupoFaturamento(), filter.getInstalacao(), filter.getMedidor());
		} else {
			return this.leituraRepository.getLeituraRepasse(filter.getIdRegional(), filter.getDataReferencia(), filter.getGrupoFaturamento(), filter.getInstalacao(), filter.getMedidor());
		}
	}

}
