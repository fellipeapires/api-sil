package br.com.sil.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sil.config.ApiProperty;
import br.com.sil.model.Exportacao;
import br.com.sil.model.GrupoFaturamento;
import br.com.sil.model.Regional;
import br.com.sil.model.Usuario;
import br.com.sil.repository.ExportacaoRepository;
import br.com.sil.repository.filter.ExportacaoFilter;
import br.com.sil.repository.projection.ExportacaoProjection;
import br.com.sil.repository.projection.RetornoLeituraExportacaoProjection;
import br.com.sil.service.interfaces.IExportacaoService;

@Service
public class ExportacaoService implements IExportacaoService {
	
	@Autowired
	private ApiProperty apiProperty;

	@Autowired
	private ExportacaoRepository exportacaoRepository;
	
	@Autowired
	private RetornoLeituraService retornoLeituraService;
	
	@Autowired
	private RegionalService regionalService;

	@Autowired
	private GrupoFaturamentoService grupoFaturamentoService;

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public Exportacao incluir(Exportacao entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exportacao alterar(Exportacao entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exportacao buscarPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<ExportacaoProjection> listar(ExportacaoFilter filter) {
		return this.exportacaoRepository.listar(filter.getIdRegional(), filter.getDataReferencia(), filter.getGrupoFaturamento());
	}
	
	@Override
	public List<Exportacao> pesquisar(ExportacaoFilter filter) {
		return this.exportacaoRepository.pesquisar(filter);
	}
	
	public Exportacao exportar(ExportacaoFilter filter) throws Exception {
		int qtdExportacao = 0;
		LocalDateTime dataExportacao = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
		System.out.println("Data Inicio Exportacao: " + dataExportacao);
		
		GrupoFaturamento grupoFaturamento = this.grupoFaturamentoService.findByCodigo(filter.getGrupoFaturamento()).get();
		Usuario usuario = this.usuarioService.buscarPorId(filter.getIdUsuario());
		Regional regional = this.regionalService.buscarPorId(filter.getIdRegional());
		List<Long> listaIdRetornoLeitura = new ArrayList<>();
		int qtdImportado = this.exportacaoRepository.getQtdImportado(filter.getIdRegional(), filter.getDataReferencia(), filter.getGrupoFaturamento());
		int qtdExportado = this.exportacaoRepository.getQtdExportado(filter.getIdRegional(), filter.getDataReferencia(), filter.getGrupoFaturamento());
		int qtdNaoExportado = this.exportacaoRepository.getQtdNaoExportado(filter.getIdRegional(), filter.getDataReferencia(), filter.getGrupoFaturamento());
		
		String cabecarioArquivo = this.exportacaoRepository.getCabecarioArquivo(filter.getIdRegional(), filter.getDataReferencia(), filter.getGrupoFaturamento(), filter.getTipoLeitura());
		
		List<RetornoLeituraExportacaoProjection> listaRetorno = this.retornoLeituraService.getRetornoLeituraExportacao(filter.getIdRegional(), filter.getDataReferencia(), filter.getGrupoFaturamento(), filter.getTipoLeitura(), filter.getIsDataFim());
		String nomeArquivo = "";
		
		if (listaRetorno.size() > 0) {
			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			if (filter.getIdRegional() == 1) {
				if (filter.getTipoLeitura().equalsIgnoreCase("S")) {
					nomeArquivo = "L12RMSP2";
				} else if (filter.getTipoLeitura().equalsIgnoreCase("N")) {
					nomeArquivo = "LRMSP2";
				} else if (filter.getTipoLeitura().equalsIgnoreCase("R")) {
					nomeArquivo = "RRMSP2";
				} else {
					nomeArquivo = "Default";
				}
				cabecarioArquivo += "RMSP2";
			} else {
				if (filter.getTipoLeitura().equalsIgnoreCase("S")) {
					nomeArquivo = "L12V.Paraiba";
				} else if (filter.getTipoLeitura().equalsIgnoreCase("N")) {
					nomeArquivo = "LV.Paraiba";
				} else if (filter.getTipoLeitura().equalsIgnoreCase("R")) {
					nomeArquivo = "RV.Paraiba";
				} else {
					nomeArquivo = "Default";
				}
				cabecarioArquivo += "V.Paraiba";
			}
			nomeArquivo = nomeArquivo + dtFormat.format(new Date()) + ".txt";
			// ArquivoRetornoResource.nomeArquivo = nomeArquivo; VERIFICAR ANTES DE DELETAR
			FileWriter arquivoExportacao = new FileWriter(this.apiProperty.getPathExportacao() + nomeArquivo);
			PrintWriter gravar = new PrintWriter(arquivoExportacao);
			
			Exportacao exportacao = new Exportacao();
			exportacao.setGrupoFaturamento(grupoFaturamento);
			exportacao.setRegional(regional);
			exportacao.setUsuario(usuario);
			exportacao.setNome(nomeArquivo);
			exportacao.setQtdExportacao(listaRetorno.size());
			exportacao.setDataExportacao(dataExportacao);
			exportacao.setDataReferencia(filter.getDataReferencia());
			exportacao.setObservacao("");
			exportacao.setPath(this.apiProperty.getPathExportacao() + nomeArquivo);
			exportacao.setQtdImportado(qtdImportado);
			exportacao.setQtdExportado(qtdExportado);
			exportacao.setQtdNaoExportado(qtdNaoExportado);
			Exportacao exportacaoSalva = this.exportacaoRepository.save(exportacao);
			
			gravar.printf(cabecarioArquivo + "\r\n");
			try {
				for (RetornoLeituraExportacaoProjection retorno : listaRetorno) {
					qtdExportacao++;
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
					//DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
					String data = retorno.getDataLeitura().format(formatter);
					String hora = retorno.getHoraLeitura().replaceFirst(":", "");
					hora = hora.split(":")[0];
					gravar.printf("01");// Numero de linha (2 digitos)
					gravar.printf(data);// 8 digitos;
					gravar.printf(hora);// 4 digitos;
					gravar.printf(retorno.getNumeroLeiturista());// 3 digitos;
					gravar.printf(retorno.getOrdemLeitura());// 20 digitos;
					gravar.printf(retorno.getUnidadeLeitura());// 8 digitos;
					gravar.printf(retorno.getCliente());// 50 digitos;
					gravar.printf(retorno.getEndereco());// 82 digitos;
					gravar.printf(retorno.getComplemento());// 20 digitos;
					gravar.printf(retorno.getMunicipio());// 40 digitos;
					gravar.printf(retorno.getCep());// 9 digitos;
					gravar.printf(retorno.getInstalacao());// 10 digitos;
					gravar.printf(retorno.getCodigoLogradouro());// 12 digitos;
					gravar.printf(retorno.getMedidor());// 12 digitos ou 18
					gravar.printf(retorno.getTipoMedidor());// 7 digitos;
					gravar.printf(retorno.getSequencia());// 2 digitos;
					gravar.printf(retorno.getUnidadeMedida());// 4 digitos;
					gravar.printf(retorno.getMensAvisoMobile());// 30 digitos;
					gravar.printf(retorno.getSeguimento());// 2 digitos;
					gravar.printf(retorno.getRamoAtividade());// 20 digitos;
					gravar.printf(String.valueOf(String.format("%017d", retorno.getUltimaLeitura())));// 17
					gravar.printf(String.valueOf(String.format("%017d", retorno.getMediaTresMeses())));// 17
					gravar.printf(String.valueOf(String.format("%017d", retorno.getLeituraMedida())));// 17
					if (retorno.getCodigoOcorrencia() == 0) {
						gravar.printf(String.valueOf(String.format("%2s", "")));// 2 digitos;
					} else {
						gravar.printf(String.valueOf(String.format("%02d", retorno.getCodigoOcorrencia())));// 2
					}
					gravar.printf(retorno.getLeituraRepasse());// 1 digitos;
					gravar.printf(String.valueOf(String.format("%04d", Integer.parseInt(retorno.getTarefaLeitura()))));// 4 digitos;
					gravar.printf(String.valueOf(String.format("%04d", retorno.getOrdenacaoLeitura())));// 4 digitos;
					gravar.printf(String.valueOf(String.format("%04d", Integer.parseInt(retorno.getTarefaEntrega()))));// 4 digitos;
					gravar.printf(String.valueOf(retorno.getMatriculaColaborador()));// 8 digitos;
					if (retorno.getLatitude() == null || retorno.getLatitude().equals("0")) {
						gravar.printf(String.format("%8s", "") + " " + String.format("%8s", ""));
					} else {
						gravar.printf(retorno.getLatitude() + "," + retorno.getLongitude());// 17 digitos;
					}
					gravar.printf(retorno.getObservacao() + "\r\n");
					listaIdRetornoLeitura.add(retorno.getIdRetornoLeitura());
				}
			} catch (Exception e) {
				e.printStackTrace();
				exportacaoSalva.setObservacao("ERRO: " + exportacao.getId() + "Linha.: " + qtdExportacao + " - " + e.getMessage());
				this.exportacaoRepository.save(exportacaoSalva);
				System.out.println("Causa: " + e.getCause());
				System.out.println("Mensagem Localizada " + e.getLocalizedMessage());
				System.out.println("Mensagem " + e.getMessage());
			} finally {
				try {
					arquivoExportacao.close();
					dataExportacao = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
					System.out.println("Data Fim Exportacao: " + dataExportacao + " - qtd: " + qtdExportacao);
					exportacaoSalva.setObservacao("Exportado com sucesso!");
					exportacaoSalva.setQtdExportacao(qtdExportacao);
					this.exportacaoRepository.save(exportacaoSalva);
					this.retornoLeituraService.marcarExportado(listaIdRetornoLeitura);
					return exportacaoSalva;
				} catch (IOException e) {
					System.out.println("Error while flushing/closing fileWriter !!!");
					e.printStackTrace();
				}
			}
		}
		dataExportacao = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
		System.out.println("Data Fim Exportacao: " + dataExportacao + " - qtd: " + qtdExportacao);
		return null;
	}
	
	
	
}
