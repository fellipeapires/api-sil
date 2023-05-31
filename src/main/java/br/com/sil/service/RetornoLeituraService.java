package br.com.sil.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.sil.model.DescricaoLog;
import br.com.sil.model.GrupoFaturamento;
import br.com.sil.model.Leitura;
import br.com.sil.model.Ocorrencia;
import br.com.sil.model.OcorrenciaTipo;
import br.com.sil.model.Regional;
import br.com.sil.model.RetornoLeitura;
import br.com.sil.model.RetornoLeituraInfo;
import br.com.sil.model.StatusRegitro;
import br.com.sil.model.Usuario;
import br.com.sil.model.dto.RetornoLeituraDto;
import br.com.sil.model.dto.RetornoLeituraMobileDto;
import br.com.sil.repository.RetornoLeituraRepository;
import br.com.sil.repository.filter.RetornoLeituraFilter;
import br.com.sil.repository.projection.AcompanhamentoDetailProjection;
import br.com.sil.repository.projection.AcompanhamentoProjection;
import br.com.sil.repository.projection.AcompanhamentoTotalProjection;
import br.com.sil.repository.projection.MapaProjection;
import br.com.sil.repository.projection.RetornoLeituraExportacaoProjection;
import br.com.sil.repository.projection.RetornoLeituraProjection;
import br.com.sil.service.interfaces.IRetornoLeituraService;

@Service
public class RetornoLeituraService implements IRetornoLeituraService {

	@Autowired
	private RetornoLeituraRepository retornoLeituraRepository;

	@Autowired
	private LeituraService leituraService;

	@Autowired
	private OcorrenciaService ocorrenciaService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RegionalService regionalService;

	@Autowired
	private GrupoFaturamentoService grupoFaturamentoService;
	
	@Autowired
	private RetornoFotoService retornoFotoService;
	
	@Override
	public RetornoLeitura incluir(RetornoLeitura entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Long> lancarLeituraMobile(List<RetornoLeituraMobileDto> listaEntity) {
		try {
			List<Long> listaIdLeitura = new ArrayList<>();
			List<RetornoLeitura> listaSalva = new ArrayList<>();
			for (RetornoLeituraMobileDto retornoMobile: listaEntity) {
				if (this.isExiste(retornoMobile.getIdLeitura()) == 0) {
					// ocorrencia é o id do objeto
					Ocorrencia ocorrencia = this.ocorrenciaService.buscarPorId(retornoMobile.getOcorrencia());
					/*if ((ocorrencia.getTipoOcorrencia() == OcorrenciaTipo.IMPEDIMENTO.getCodigo() && retornoMobile.getLeituraMedida() > 0) || (ocorrencia.getTipoOcorrencia() == OcorrenciaTipo.IMPEDIMENTO.getCodigo() && retornoMobile.getQtdFoto() == 0)) {
						return null;
					}*/
					Leitura leitura = this.leituraService.buscarPorId(retornoMobile.getIdLeitura());
					Usuario usuario = this.usuarioService.buscarPorId(retornoMobile.getIdFuncionario());
					Regional regional = this.regionalService.buscarPorId(leitura.getImportacao().getRegional().getId());
					GrupoFaturamento grupoFaturamento = this.grupoFaturamentoService.buscarPorId(leitura.getImportacao().getGrupoFaturamento().getId());
					RetornoLeitura retornoLeitura = new RetornoLeitura();
					retornoLeitura.setLeitura(leitura);
					retornoLeitura.setOcorrencia(ocorrencia);
					retornoLeitura.setUsuario(usuario);
					retornoLeitura.setRegional(regional);
					retornoLeitura.setGrupoFaturamento(grupoFaturamento);
					retornoLeitura.setDataReferencia(leitura.getImportacao().getDataReferencia());
					retornoLeitura.setDataLeitura(retornoMobile.getDataLeitura());
					retornoLeitura.setLeituraMedida(retornoMobile.getLeituraMedida());
					retornoLeitura.setLeituraAnterior(leitura.getUltimaLeitura());
					
					Double variacao = (double) (((retornoLeitura.getLeituraMedida() - retornoLeitura.getLeituraAnterior()) * 100) / (retornoLeitura.getLeitura().getMedia3Meses() == 0 ? 1 : retornoLeitura.getLeitura().getMedia3Meses()));
					retornoLeitura.setVariacaoLeitura(variacao);
					
					retornoLeitura.setTarefaLeitura(leitura.getTarefaLeitura());
					retornoLeitura.setTarefaEntrega(leitura.getTarefaEntrega());
					retornoLeitura.setInstalacao(leitura.getInstalacao());
					retornoLeitura.setMedidor(leitura.getMedidor());
					retornoLeitura.setOrdenacaoLeitura(leitura.getOrdenacaoLeitura());
					retornoLeitura.setLatitude(retornoMobile.getLatitude());
					retornoLeitura.setLongitude(retornoMobile.getLongitude());
					retornoLeitura.setFlagCritica(RetornoLeituraInfo.NAO_ANALIZADO.getCodigo());
					retornoLeitura.setObservacao(retornoMobile.getObservacao() != null ? retornoMobile.getObservacao() : "");
					retornoLeitura.setFlagMedia(0);
					retornoLeitura.setIsFoto(retornoMobile.getIsFoto() > 0 ? RetornoLeituraInfo.COM_FOTO.getCodigo() : RetornoLeituraInfo.SEM_FOTO.getCodigo());
					//retornoLeitura.setIsFoto(retornoMobile.getIsFoto());
					retornoLeitura.setAtivo(StatusRegitro.ATIVO.getCodigo());
					//RetornoLeitura retornoLeituraSalvo = this.retornoLeituraRepository.save(retornoLeitura);
					/*if (retornoLeituraSalvo != null) {
						this.retornoLeituraRepository.setLogLancamentoLeitura(entity.getIdUsuarioAlteracao(), retornoLeitura.getId(), retornoLeitura.getId(), DescricaoLog.LANCAMENTO_LEITURA.getNome(), LocalDateTime.now());
						if (entity.getListaFoto().size() > 0) {					
							this.retornoFotoService.upload(entity.getListaFoto(), entity.getIdUsuarioAlteracao());
						}
					}*/
					listaSalva.add(retornoLeitura);
				}
				listaIdLeitura.add(retornoMobile.getIdLeitura());
			}
			if (listaSalva.size() > 0) {				
				this.retornoLeituraRepository.saveAll(listaSalva);
			}
			return this.retornoLeituraRepository.getListaIdLeitura(listaIdLeitura);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public RetornoLeitura lancarLeitura(RetornoLeituraDto entity) {
		try {
			if (this.isExiste(entity.getIdLeitura()) > 0) {
				return null;
			}
			Ocorrencia ocorrencia = this.ocorrenciaService.findByCodigo(entity.getOcorrencia()).get();
			if ((ocorrencia.getTipoOcorrencia() == OcorrenciaTipo.IMPEDIMENTO.getCodigo() && entity.getLeitura() > 0) || (ocorrencia.getTipoOcorrencia() == OcorrenciaTipo.IMPEDIMENTO.getCodigo() && entity.getQtdFoto() == 0)) {
				return null;
			}
			Leitura leitura = this.leituraService.buscarPorId(entity.getIdLeitura());
			Usuario usuario = this.usuarioService.buscarPorId(entity.getIdUsuario());
			Regional regional = this.regionalService.buscarPorId(leitura.getImportacao().getRegional().getId());
			GrupoFaturamento grupoFaturamento = this.grupoFaturamentoService.buscarPorId(leitura.getImportacao().getGrupoFaturamento().getId());
			RetornoLeitura retornoLeitura = new RetornoLeitura();
			retornoLeitura.setLeitura(leitura);
			retornoLeitura.setOcorrencia(ocorrencia);
			retornoLeitura.setUsuario(usuario);
			retornoLeitura.setRegional(regional);
			retornoLeitura.setGrupoFaturamento(grupoFaturamento);
			retornoLeitura.setDataReferencia(leitura.getImportacao().getDataReferencia());
			retornoLeitura.setDataLeitura(entity.getDataLeitura());
			retornoLeitura.setLeituraMedida(entity.getLeitura());
			retornoLeitura.setLeituraAnterior(leitura.getUltimaLeitura());
			
			Double variacao = (double) (((retornoLeitura.getLeituraMedida() - retornoLeitura.getLeituraAnterior()) * 100) / (retornoLeitura.getLeitura().getMedia3Meses() == 0 ? 1 : retornoLeitura.getLeitura().getMedia3Meses()));
			retornoLeitura.setVariacaoLeitura(variacao);
			
			retornoLeitura.setTarefaLeitura(leitura.getTarefaLeitura());
			retornoLeitura.setTarefaEntrega(leitura.getTarefaEntrega());
			retornoLeitura.setInstalacao(leitura.getInstalacao());
			retornoLeitura.setMedidor(leitura.getMedidor());
			retornoLeitura.setOrdenacaoLeitura(leitura.getOrdenacaoLeitura());
			retornoLeitura.setLatitude("0");
			retornoLeitura.setLongitude("0");
			retornoLeitura.setFlagCritica(RetornoLeituraInfo.NAO_ANALIZADO.getCodigo());
			retornoLeitura.setObservacao(entity.getObservacao());
			retornoLeitura.setFlagMedia(0);
			retornoLeitura.setIsFoto(entity.getQtdFoto() > 0 ? RetornoLeituraInfo.COM_FOTO.getCodigo() : RetornoLeituraInfo.SEM_FOTO.getCodigo());
			retornoLeitura.setAtivo(StatusRegitro.ATIVO.getCodigo());
			RetornoLeitura retornoLeituraSalvo = this.retornoLeituraRepository.save(retornoLeitura);
			if (retornoLeituraSalvo != null) {
				this.retornoLeituraRepository.setLogLancamentoLeitura(entity.getIdUsuarioAlteracao(), retornoLeitura.getId(), retornoLeitura.getId(), DescricaoLog.LANCAMENTO_LEITURA.getNome(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
				if (entity.getListaFoto().size() > 0) {					
					this.retornoFotoService.upload(entity.getListaFoto(), entity.getIdUsuarioAlteracao());
				}
			}
			return retornoLeituraSalvo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public RetornoLeitura lancarRepasse(RetornoLeituraDto entity) {
		try {
			if (this.isExiste(entity.getIdLeituraRepasse()) > 0) {
				return null;
			}
			Ocorrencia ocorrencia = this.ocorrenciaService.findByCodigo(entity.getOcorrencia()).get();
			if ((ocorrencia.getTipoOcorrencia() == OcorrenciaTipo.IMPEDIMENTO.getCodigo() && entity.getLeitura() > 0) || (ocorrencia.getTipoOcorrencia() == OcorrenciaTipo.IMPEDIMENTO.getCodigo() && entity.getQtdFoto() == 0)) {
				return null;
			}
			Leitura leitura = this.leituraService.buscarPorId(entity.getIdLeituraRepasse());
			Usuario usuario = this.usuarioService.buscarPorId(entity.getIdUsuario());
			Regional regional = this.regionalService.buscarPorId(leitura.getImportacao().getRegional().getId());
			GrupoFaturamento grupoFaturamento = this.grupoFaturamentoService.buscarPorId(leitura.getImportacao().getGrupoFaturamento().getId());
			RetornoLeitura retornoLeitura = new RetornoLeitura();
			retornoLeitura.setLeitura(leitura);
			retornoLeitura.setOcorrencia(ocorrencia);
			retornoLeitura.setUsuario(usuario);
			retornoLeitura.setRegional(regional);
			retornoLeitura.setGrupoFaturamento(grupoFaturamento);
			retornoLeitura.setDataReferencia(leitura.getImportacao().getDataReferencia());
			retornoLeitura.setDataLeitura(entity.getDataLeitura());
			retornoLeitura.setLeituraMedida(entity.getLeitura());
			retornoLeitura.setLeituraAnterior(leitura.getUltimaLeitura());
			
			Double variacao = (double) (((retornoLeitura.getLeituraMedida() - retornoLeitura.getLeituraAnterior()) * 100) / (retornoLeitura.getLeitura().getMedia3Meses() == 0 ? 1 : retornoLeitura.getLeitura().getMedia3Meses()));
			retornoLeitura.setVariacaoLeitura(variacao);
			
			retornoLeitura.setTarefaLeitura(leitura.getTarefaLeitura());
			retornoLeitura.setTarefaEntrega(leitura.getTarefaEntrega());
			retornoLeitura.setInstalacao(leitura.getInstalacao());
			retornoLeitura.setMedidor(leitura.getMedidor());
			retornoLeitura.setOrdenacaoLeitura(leitura.getOrdenacaoLeitura());
			retornoLeitura.setLatitude("0");
			retornoLeitura.setLongitude("0");
			retornoLeitura.setFlagCritica(RetornoLeituraInfo.NAO_ANALIZADO.getCodigo());
			retornoLeitura.setObservacao(entity.getObservacao());
			retornoLeitura.setFlagMedia(0);
			retornoLeitura.setIsFoto(entity.getQtdFoto() > 0 ? RetornoLeituraInfo.COM_FOTO.getCodigo() : RetornoLeituraInfo.SEM_FOTO.getCodigo());
			retornoLeitura.setAtivo(StatusRegitro.ATIVO.getCodigo());
			RetornoLeitura retornoLeituraSalvo = this.retornoLeituraRepository.save(retornoLeitura);
			if (retornoLeituraSalvo != null) {
				this.retornoLeituraRepository.setLogLancamentoLeitura(entity.getIdUsuarioAlteracao(), retornoLeitura.getId(), retornoLeitura.getId(),  DescricaoLog.LANCAMENTO_REPASSE.getNome(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
				this.retornoFotoService.incluirRepasse(entity.getIdLeituraRepasse(), entity.getIdUsuario(), entity.getIdLeituraPasse());
				if (entity.getListaFoto().size() > 0) {					
					this.retornoFotoService.upload(entity.getListaFoto(), entity.getIdUsuarioAlteracao());
				}
			}
			return retornoLeituraSalvo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public RetornoLeitura alterarDadosLeitura(RetornoLeituraDto entity) {
		try {
			Ocorrencia ocorrencia = this.ocorrenciaService.findByCodigo(entity.getOcorrencia()).get();
			if ((ocorrencia.getTipoOcorrencia() == OcorrenciaTipo.IMPEDIMENTO.getCodigo() && entity.getLeitura() > 0) || (ocorrencia.getTipoOcorrencia() == OcorrenciaTipo.IMPEDIMENTO.getCodigo() && entity.getQtdFoto() == 0)) {
				return null;
			}
			RetornoLeitura r = this.buscarPorId(entity.getId());
			RetornoLeitura retornoLeitura = new RetornoLeitura();
			retornoLeitura.setLeitura(r.getLeitura());
			retornoLeitura.setOcorrencia(ocorrencia);
			retornoLeitura.setUsuario(r.getUsuario());
			retornoLeitura.setRegional(r.getRegional());
			retornoLeitura.setGrupoFaturamento(r.getGrupoFaturamento());
			retornoLeitura.setDataReferencia(r.getLeitura().getImportacao().getDataReferencia());
			retornoLeitura.setDataLeitura(r.getDataLeitura());
			retornoLeitura.setLeituraMedida(entity.getLeitura());
			retornoLeitura.setLeituraAnterior(r.getLeitura().getUltimaLeitura());
			
			Double variacao = (double) (((retornoLeitura.getLeituraMedida() - retornoLeitura.getLeituraAnterior()) * 100) / (retornoLeitura.getLeitura().getMedia3Meses() == 0 ? 1 : retornoLeitura.getLeitura().getMedia3Meses()));
			retornoLeitura.setVariacaoLeitura(variacao);
			
			retornoLeitura.setTarefaLeitura(r.getLeitura().getTarefaLeitura());
			retornoLeitura.setTarefaEntrega(r.getLeitura().getTarefaEntrega());
			retornoLeitura.setInstalacao(r.getLeitura().getInstalacao());
			retornoLeitura.setMedidor(r.getLeitura().getMedidor());
			retornoLeitura.setOrdenacaoLeitura(r.getOrdenacaoLeitura());
			retornoLeitura.setLatitude(r.getLatitude());
			retornoLeitura.setLongitude(r.getLongitude());
			retornoLeitura.setFlagCritica(RetornoLeituraInfo.ANALIZADO.getCodigo());
			retornoLeitura.setObservacao(entity.getObservacao());
			retornoLeitura.setFlagMedia(r.getFlagMedia());
			retornoLeitura.setIsFoto(entity.getQtdFoto() > 0 ? RetornoLeituraInfo.COM_FOTO.getCodigo() : RetornoLeituraInfo.SEM_FOTO.getCodigo());
			retornoLeitura.setAtivo(StatusRegitro.ATIVO.getCodigo());
			RetornoLeitura retornoLeituraSalvo = this.retornoLeituraRepository.save(retornoLeitura);
			RetornoLeitura retornoLeituraLog = null;
			if (retornoLeituraSalvo != null) {
				r.setDataAtualizacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
				r.setAtivo(StatusRegitro.INATIVO.getCodigo());
				retornoLeituraLog = this.retornoLeituraRepository.save(r);
			}
			if (retornoLeituraLog != null) {
				this.retornoLeituraRepository.setLogLancamentoLeitura(entity.getIdUsuarioAlteracao(), r.getId(), retornoLeitura.getId(), DescricaoLog.ALTERACAO_LEITURA.getNome(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
			}
			return retornoLeituraSalvo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		return null;
	}
	
	public RetornoLeitura setRevisao(RetornoLeituraDto entity) {
		try {
			RetornoLeitura r = this.buscarPorId(entity.getId());
			if(this.retornoLeituraRepository.isRevisado(entity.getIdLeitura()) > 0) {
				return null;
			}
			RetornoLeitura retornoLeitura = new RetornoLeitura();
			retornoLeitura.setLeitura(r.getLeitura());
			retornoLeitura.setOcorrencia(r.getOcorrencia());
			retornoLeitura.setUsuario(r.getUsuario());
			retornoLeitura.setRegional(r.getRegional());
			retornoLeitura.setGrupoFaturamento(r.getGrupoFaturamento());
			retornoLeitura.setDataReferencia(r.getDataReferencia());
			retornoLeitura.setDataLeitura(r.getDataLeitura());
			retornoLeitura.setLeituraMedida(r.getLeituraMedida());
			retornoLeitura.setLeituraAnterior(r.getLeituraAnterior());
			retornoLeitura.setVariacaoLeitura(r.getVariacaoLeitura());
			retornoLeitura.setTarefaLeitura(r.getTarefaLeitura());
			retornoLeitura.setTarefaEntrega(r.getTarefaEntrega());
			retornoLeitura.setInstalacao(r.getInstalacao());
			retornoLeitura.setMedidor(r.getMedidor());
			retornoLeitura.setOrdenacaoLeitura(r.getOrdenacaoLeitura());
			retornoLeitura.setLatitude(r.getLatitude());
			retornoLeitura.setLongitude(r.getLongitude());
			retornoLeitura.setFlagCritica(RetornoLeituraInfo.ANALIZADO.getCodigo());
			retornoLeitura.setObservacao(r.getObservacao());
			retornoLeitura.setFlagMedia(r.getFlagMedia());
			retornoLeitura.setIsFoto(r.getIsFoto());
			retornoLeitura.setAtivo(StatusRegitro.ATIVO.getCodigo());
			RetornoLeitura retornoLeituraSalvo = this.retornoLeituraRepository.save(retornoLeitura);
			RetornoLeitura retornoLeituraLog = null;
			if (retornoLeituraSalvo != null) {
				r.setDataAtualizacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
				r.setAtivo(StatusRegitro.INATIVO.getCodigo());
				retornoLeituraLog = this.retornoLeituraRepository.save(r);
			}
			if (retornoLeituraLog != null) {
				this.retornoLeituraRepository.setLogLancamentoLeitura(entity.getIdUsuarioAlteracao(), r.getId(), retornoLeitura.getId(), DescricaoLog.REVISAO_LEITURA.getNome(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
			}
			return retornoLeituraSalvo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		return null;
	}

	@Override
	public RetornoLeitura alterar(RetornoLeitura entity) {
		return this.retornoLeituraRepository.save(entity);
	}

	@Override
	public RetornoLeitura buscarPorId(long id) {
		return this.retornoLeituraRepository.findById(id).get();
	}

	public Page<RetornoLeitura> pesquisar(RetornoLeituraFilter filter, Pageable pageable) {
		return this.retornoLeituraRepository.pesquisar(filter, pageable);
	}
	
	@Override
	public List<RetornoLeitura> pesquisar(RetornoLeituraFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AcompanhamentoProjection> getAcompanhamento(RetornoLeituraFilter filter) {
		return this.retornoLeituraRepository.getAcompanhamento(filter.getIdRegional(), filter.getDataReferencia(),
				filter.getGrupoFaturamento(), filter.getSegmento(), filter.getIdUsuario(), filter.getMsgMobile());
	}

	public AcompanhamentoTotalProjection getAcompanhamentoTotal(RetornoLeituraFilter filter) {
		return this.retornoLeituraRepository.getAcompanhamentoTotal(filter.getIdRegional(), filter.getDataReferencia(),
				filter.getGrupoFaturamento());
	}

	public List<AcompanhamentoDetailProjection> getAcompanhamentoDetail(RetornoLeituraFilter filter) {
		return this.retornoLeituraRepository.getAcompanhamentoDetail(filter.getIdRegional(), filter.getDataReferencia(),
				filter.getGrupoFaturamento(), filter.getTarefa(), filter.getIdUsuario());
	}

	public List<MapaProjection> getMapa(RetornoLeituraFilter filter) {
		return this.retornoLeituraRepository.getMapa(filter.getIdRegional(), filter.getDataReferencia(),
				filter.getGrupoFaturamento(), filter.getTarefa(), filter.getIdUsuario());
	}

	public List<MapaProjection> getMapaPorRetornoLeitura(long id) {
		return this.retornoLeituraRepository.getMapaPorRetornoLeitura(id);
	}
	
	public List<MapaProjection> getMapaRoteiro(RetornoLeituraFilter filter) {
		return this.retornoLeituraRepository.getMapaRoteiro(filter.getIdRegional(), filter.getDataReferencia(),
				filter.getGrupoFaturamento(), filter.getTarefa(), filter.getIdUsuario());
	}

	public List<RetornoLeituraProjection> getRetornoLeituraDetail(RetornoLeituraFilter filter) {
		return this.retornoLeituraRepository.getRetornoLeituraDetail(filter.getDataReferencia(), filter.getIdRegional(), filter.getGrupoFaturamento(), filter.getInstalacao(), filter.getMedidor());
	}
	
	public List<RetornoLeituraExportacaoProjection> getRetornoLeituraExportacao(Long idRegional, LocalDate dataReferencia, int grupoFaturamento, String tipoLeitura, boolean isDataFim) {
		if (isDataFim) {
			return this.retornoLeituraRepository.getRetornoLeituraExportacaoDataFim(idRegional, dataReferencia, grupoFaturamento, tipoLeitura);
		} else {
			return this.retornoLeituraRepository.getRetornoLeituraExportacao(idRegional, dataReferencia, grupoFaturamento, tipoLeitura);
		}
	}
	
	public List<RetornoLeituraProjection> getUltimosTresMeses(RetornoLeituraFilter filter) {
		return this.retornoLeituraRepository.getUltimosTresMeses(filter.getDataReferencia(), filter.getIdRegional(), filter.getIdGrupoFaturamento(), filter.getInstalacao(), filter.getMedidor());
	}
	
	public int isExiste(long idleitura) {
		return this.retornoLeituraRepository.isExiste(idleitura);
	}

}
