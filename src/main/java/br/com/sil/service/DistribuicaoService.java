package br.com.sil.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sil.model.StatusDistribuicao;
import br.com.sil.model.Distribuicao;
import br.com.sil.model.TipoDistribuicao;
import br.com.sil.model.dto.DistribuicaoDto;
import br.com.sil.repository.DistribuicaoRepository;
import br.com.sil.repository.filter.DistribuicaoFilter;
import br.com.sil.repository.projection.CargaMobileProjection;
import br.com.sil.repository.projection.DesassociadoProjection;
import br.com.sil.repository.projection.DistribuicaoProjection;
import br.com.sil.repository.projection.DistribuidoDetailProjection;
import br.com.sil.repository.projection.DistribuidoProjection;
import br.com.sil.service.interfaces.IDistribuicaoService;

@Service
public class DistribuicaoService implements IDistribuicaoService {

	@Autowired
	private DistribuicaoRepository distribuicaoRepository;
	
	@Override
	public Distribuicao incluir(Distribuicao entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer incluir(List<DistribuicaoDto> lista) {
		int qtd = 0;
		for (DistribuicaoDto entity : lista) {
			qtd += this.distribuicaoRepository.incluir(entity.getIdRegional(), entity.getDataReferencia(), 
					entity.getGrupoFaturamento(), entity.getIdUsuario(), entity.getTarefa(),
					entity.getEndereco(), entity.getIdLeitura(), StatusDistribuicao.NAO_LIBERADO.getCodigo(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
		}
		return qtd;
	}

	public Integer duplicar(DistribuicaoDto entity) {
		return this.distribuicaoRepository.duplicar(entity.getIdRegional(), entity.getDataReferencia(),
				entity.getGrupoFaturamento(), entity.getTarefa(), entity.getIdUsuario(), entity.getIdUsuarioAtribuido(), StatusDistribuicao.NAO_LIBERADO.getCodigo(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
	}
	
	public Integer desassociar(String dataReferencia, long idRegional, int grupoFaturamento, String tarefa, long idUsuario) {
		List<DesassociadoProjection> lista = this.distribuicaoRepository.getDesassociado(idRegional, dataReferencia, grupoFaturamento, tarefa, idUsuario);
		int qtd = this.distribuicaoRepository.desassociar(idRegional, dataReferencia, grupoFaturamento, tarefa, idUsuario);
		if (qtd > 0) {
			for (DesassociadoProjection d : lista) {
				this.distribuicaoRepository.incluirDesassociado(d.getIdUsuario(), d.getIdLeitura());
			}
		}
		return qtd;
	}
	
	public Integer desassociarIndividual(long idDistribuicao) {
		DesassociadoProjection desassociado = this.distribuicaoRepository.getDesassociadoIndividual(idDistribuicao);
		int qtd = this.distribuicaoRepository.desassociarIndividual(idDistribuicao);
		if (qtd > 0 && desassociado != null) {
			this.distribuicaoRepository.incluirDesassociado(desassociado.getIdUsuario(), desassociado.getIdLeitura());
		}
		return qtd;
	}
	
	public Integer desassociarPorEndereco(DistribuicaoDto entity) {
		this.distribuicaoRepository.incluirDesassociadoPorEndereco(entity.getDataReferencia(), entity.getIdRegional(), entity.getGrupoFaturamento(), entity.getTarefa(), entity.getEndereco(), entity.getIdUsuario());
		return this.distribuicaoRepository.desassociarPorEndereco(entity.getDataReferencia(), entity.getIdRegional(), entity.getGrupoFaturamento(), entity.getTarefa(), entity.getEndereco(), entity.getIdUsuario());
	}

	@Override
	public Distribuicao alterar(Distribuicao entity) {
		return this.distribuicaoRepository.save(entity);
	}     

	@Override
	public Distribuicao buscarPorId(long id) {
		return this.distribuicaoRepository.findById(id).get();
	}

	@Override
	public List<Distribuicao> pesquisar(DistribuicaoFilter filter) {
		return this.distribuicaoRepository.pesquisar(filter);
	}
	
	public List<DistribuidoProjection> getDistribuido(DistribuicaoFilter filter) {
		return this.distribuicaoRepository.getDistribuido(filter.getDataReferencia(), filter.getIdRegional(), filter.getGrupoFaturamento());
	}
	
	public List<DistribuidoDetailProjection> getDistribuidoDetail(DistribuicaoFilter filter) {
		return this.distribuicaoRepository.getDistribuidoDetail(filter.getIdRegional(), filter.getDataReferencia(), filter.getGrupoFaturamento(), filter.getTarefa(), filter.getIdUsuario());
	}

	public List<DistribuicaoProjection> getPendente(DistribuicaoFilter filter) {
		if (filter.getTipoDistribuicao() == TipoDistribuicao.TAREFA.getCodigo()) {
			return this.distribuicaoRepository.getPendentesPorTarefa(filter.getDataReferencia(),
					filter.getIdRegional(), filter.getGrupoFaturamento());
		} else if (filter.getTipoDistribuicao() == TipoDistribuicao.ENDERECO.getCodigo()) {
			return this.distribuicaoRepository.getPendentesPorEndereco(filter.getDataReferencia(),
					filter.getIdRegional(), filter.getGrupoFaturamento());
		} else if (filter.getTipoDistribuicao() == TipoDistribuicao.INDIVIDUAL.getCodigo()) {
			return this.distribuicaoRepository.getPendentesPorInstalacao(filter.getDataReferencia(),
					filter.getIdRegional(), filter.getGrupoFaturamento());
		} else {
			return null;
		}
	}
	
	public List<DistribuidoProjection> listarCarga(DistribuicaoFilter filter) {
		return this.distribuicaoRepository.listarCarga(filter.getDataReferencia(), filter.getIdRegional(), filter.getGrupoFaturamento());
	}
	
	public Integer liberarCarga(DistribuicaoDto entity) {
		return this.distribuicaoRepository.liberarCarga(entity.getDataReferencia(), entity.getIdRegional(), entity.getGrupoFaturamento(), entity.getTarefa(), entity.getIdUsuario(), StatusDistribuicao.LIBERADO.getCodigo(), StatusDistribuicao.NAO_LIBERADO.getCodigo());
	}
	
	public List<Long> getDesassociadoMobile(Long idUsuario) {
		return this.distribuicaoRepository.getDesassociadoMobile(idUsuario);
	}
	
	public List<CargaMobileProjection> getCargaMobile(Long idUsuario) {
		return this.distribuicaoRepository.getCargaMobile(idUsuario);
	}
	
	public void alterarAssociadoMobile(DistribuicaoDto entity) {
		this.distribuicaoRepository.alterarAssociadoMobile(entity.getIdUsuario(), entity.getListaIdLeitura());
	}
	
	public void zerarDesassociadoMobile(DistribuicaoDto entity) {
		this.distribuicaoRepository.zerarDesassociadoMobile(entity.getIdUsuario(), entity.getListaIdLeitura());
	}
	
	public void marcarNoColetor(DistribuicaoDto entity) {
		this.distribuicaoRepository.marcarNoColetor(entity.getDataReferencia(), entity.getIdRegional());
	}

}
