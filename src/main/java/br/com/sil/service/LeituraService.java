package br.com.sil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.sil.model.Leitura;
import br.com.sil.model.dto.LeituraDto;
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
	
	public void saveAll(List<Leitura> lista) {
		this.leituraRepository.saveAll(lista);
	}

	@Override
	public Leitura alterar(Leitura entity) {
		return this.leituraRepository.save(entity);
	}

	@Override
	public Leitura buscarPorId(long id) {
		return this.leituraRepository.findById(id).get();
	}
	
	public List<Leitura> listarLancamento(LeituraFilter filter) {
		return this.leituraRepository.listarLancamento(filter.getIdRegional(), filter.getDataReferencia(), filter.getGrupoFaturamento(), filter.getTarefa());
	}
	
	public Page<Leitura> pesquisar(LeituraFilter filter, Pageable pageable) {
		return this.leituraRepository.pesquisar(filter, pageable);
	}

	@Override
	public List<Leitura> pesquisar(LeituraFilter filter) {
		return null;
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
	
	public int alterarTarefa(LeituraDto entity) {
		return this.leituraRepository.alterarTarefa(entity.getIdRegional(), entity.getDataReferencia(), entity.getGrupoFaturamento(), entity.getId(), entity.getTarefaLeitura(), entity.getTarefaEntrega());
	}
	
	public int alterarTarefaPorEndereco(LeituraDto entity) {
		if (entity.getOpcaoAlteracaoTarefa() == 1) {
			return this.leituraRepository.setTarefaLeituraPorEndereco(entity.getIdRegional(), entity.getDataReferencia(), entity.getGrupoFaturamento(), entity.getEndereco(), entity.getNumero(), entity.getTarefaLeitura());
		} else if (entity.getOpcaoAlteracaoTarefa() == 2) {
			return this.leituraRepository.setTarefaEntregaPorEndereco(entity.getIdRegional(), entity.getDataReferencia(), entity.getGrupoFaturamento(), entity.getEndereco(), entity.getNumero(), entity.getTarefaEntrega());
		} else if (entity.getOpcaoAlteracaoTarefa() ==3) {
			return this.leituraRepository.setTarefaLeituraEntregaPorEndereco(entity.getIdRegional(), entity.getDataReferencia(), entity.getGrupoFaturamento(), entity.getEndereco(), entity.getNumero(), entity.getTarefaLeitura(), entity.getTarefaEntrega());
		} else {
			return 0;
		}
	}

}
