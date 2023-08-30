package br.com.sil.service;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.sil.config.ApiProperty;
import br.com.sil.model.GrupoFaturamento;
import br.com.sil.model.Importacao;
import br.com.sil.model.Leitura;
import br.com.sil.model.Regional;
import br.com.sil.model.Usuario;
import br.com.sil.repository.ImportacaoRepository;
import br.com.sil.repository.filter.ImportacaoFilter;
import br.com.sil.service.interfaces.IImportacaoService;

@Service
public class ImportacaoService implements IImportacaoService {
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Autowired
	private ApiProperty apiProperty;

	@Autowired
	private ImportacaoRepository importacaoRepository;
	
	@Autowired
	private RegionalService regionalService;

	@Autowired
	private GrupoFaturamentoService grupoFaturamentoService;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LeituraService leituraService;

	@Override
	public Importacao incluir(Importacao entity) {
		return this.importacaoRepository.save(entity);
	}

	@Override
	public Importacao alterar(Importacao entity) {
		return this.importacaoRepository.save(entity);
	}

	@Override
	public Importacao buscarPorId(long id) {
		return this.importacaoRepository.findById(id).get();
	}

	@Override
	public List<Importacao> pesquisar(ImportacaoFilter filter) {
		return this.importacaoRepository.pesquisar(filter);
	}

	public Optional<Importacao> findByNome(String nome) {
		return this.importacaoRepository.findByNome(nome);
	}

	public Boolean importar(MultipartFile arquivo, long idUsuario, long idRegional, int codigoGrupoFaturamento, String dataReferencia) throws Exception {
		GrupoFaturamento grupoFaturamento = this.grupoFaturamentoService.findByCodigo(codigoGrupoFaturamento).get();
		Usuario usuario = this.usuarioService.buscarPorId(idUsuario);
		Regional regional = this.regionalService.buscarPorId(idRegional);
		
		LocalDateTime calInicio = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
		System.out.println("Data Inicio Importacao: " + calInicio);

		Importacao importacaoSalva = null;

		String data = "01/" + dataReferencia.replace("-", "/");
		LocalDate dt1 = LocalDate.parse(data, formatter);

		Importacao importacao = new Importacao();
		importacao.setNome(arquivo.getOriginalFilename());
		importacao.setDataReferencia(dt1);
		importacao.setDataImportacao(calInicio);
		importacao.setDataInicio(dt1);
		importacao.setDataFim(dt1);;
		importacao.setPath(this.apiProperty.getPathImportacao() + arquivo.getOriginalFilename());
		importacao.setRegional(regional);
		importacao.setGrupoFaturamento(grupoFaturamento);
		importacao.setUsuario(usuario);
		importacao.setQtdImportacao(0);
		importacao.setObservacao("REALIZANDO IMPORTACAO!");
		importacaoSalva = importacaoRepository.save(importacao);
		
		Leitura leitura;
		Scanner scannerHeader = new Scanner(new FileInputStream(this.apiProperty.getPathImportacao() + arquivo.getOriginalFilename()), "UTF-8");
		Scanner scanner = new Scanner(new FileInputStream(this.apiProperty.getPathImportacao() + arquivo.getOriginalFilename()), "UTF-8");
		String linhaHeader = null;
		String linha = null;
		int qtdImportada = 0;
		int percentual = 0;
		String instalacao = "";
		String tipoArquivo = arquivo.getOriginalFilename();
		List<Leitura> listaLeitura = new ArrayList<Leitura>();
		try {
			while (scannerHeader.hasNextLine()) {
				linhaHeader = scannerHeader.nextLine();
				if (linhaHeader.substring(0, 2).equals("00")) {
					//importacao.setTipoRegistro(Integer.parseInt(linhaHeader.substring(0, 2)));
					importacaoSalva.setDataInicio(this.formataDataBR(linhaHeader.substring(2, 10)));
					importacaoSalva.setDataFim(this.formataDataBR(linhaHeader.substring(10, 18)));
					//importacao.setLocalidade(linhaHeader.substring(18, linhaHeader.length()));
					importacaoSalva = importacaoRepository.save(importacaoSalva);
				}
				if (linhaHeader.substring(0, 2).equals("09")) {
					importacaoSalva.setQtdImportacao(Integer.parseInt(linhaHeader.substring(2, 11)));
					importacaoSalva = this.importacaoRepository.save(importacaoSalva);
				}
			}
			if (importacaoSalva.getId() > 0) {
				while (scanner.hasNextLine()) {
					linha = scanner.nextLine();
					if (linha.substring(0, 2).equals("01")) {
						qtdImportada++;
						leitura = new Leitura();
						leitura.setImportacao(importacaoSalva);
						leitura.setGrupoFaturamento(importacaoSalva.getGrupoFaturamento());
						leitura.setNumeroLeiturista(linha.substring(14, 17));
						leitura.setOrdemLeitura(linha.substring(17, 37));
						leitura.setUnidadeLeitura(linha.substring(37, 45));
						leitura.setCliente(linha.substring(45, 95));
						leitura.setEndereco(linha.substring(95, 177));
						leitura.setComplemento(linha.substring(177, 197));
						leitura.setMunicipio(linha.substring(197, 237));
						leitura.setCep(linha.substring(237, 246));
						leitura.setInstalacao(linha.substring(246, 256));
						instalacao = leitura.getInstalacao();
						leitura.setCodigoLogradouro(linha.substring(256, 268));
						//------------------------------------------------------
						leitura.setMedidor(linha.substring(268, 286));
						//------------------------------------------------------
						leitura.setTipoMedidor(linha.substring(286, 293));
						leitura.setSequencia(linha.substring(293, 295));
						leitura.setUnidadeMedida(linha.substring(295, 299));
						leitura.setMensAvisoMobile(linha.substring(299, 329));
						leitura.setCodigoSeguimento(linha.substring(329, 331));
						leitura.setRamoAtividade(linha.substring(331, 351));
						leitura.setUltimaLeitura(Integer.valueOf(linha.substring(351, 368)));
						leitura.setMedia3Meses(Integer.valueOf(linha.substring(368, 385)));
						leitura.setLeituraMedida(Integer.valueOf(linha.substring(385, 402)));
						leitura.setCodigoOcorrencia(linha.substring(402, 404));
						/*String ocorrencia = linha.substring(402, 404);
						if (ocorrencia.equals("  ")) {
							leitura.setOcorrencia(20);
						} else {
							leitura.setOcorrencia(Integer.parseInt(ocorrencia));
						}*/
						leitura.setFlagLeituraRepasse(linha.substring(404, 405));
						if (linha.length() > 405) {
							if (idRegional == 2) {
								String tarefaLeitura = linha.substring(406, 409).replace(" ", "9");
								//leitura.setTarefaLeitura(Integer.valueOf("9" + tarefaLeitura));
								leitura.setTarefaLeitura("9" + tarefaLeitura);
							} else {
								//leitura.setTarefaLeitura(Integer.valueOf(linha.substring(405, 409).replace(" ", "9")));
								leitura.setTarefaLeitura(linha.substring(405, 409).replace(" ", "9"));
							}
							if (linha.length() > 410) {
								String ord = linha.substring(409, 413).trim().replace(" ", "");
								ord = ord.replace("L", "");
								if (ord.length() == 0) {
									ord = "0";
								}
								leitura.setOrdenacaoLeitura(Integer.valueOf(ord));
								if (linha.length() > 414) {
									if (idRegional == 2) {
										//leitura.setTarefaEntrega(Integer.parseInt("9" + linha.substring(414, 417).replace(" ", "9")));
										leitura.setTarefaEntrega("9" + linha.substring(414, 417).replace(" ", "9"));
									} else {
										//leitura.setTarefaEntrega(Integer.parseInt(linha.substring(413, 417).replace(" ", "9")));
										leitura.setTarefaEntrega(linha.substring(413, 417).replace(" ", "9"));
									}
								} else {
									leitura.setTarefaEntrega("9999");
								}
							} else {
								leitura.setOrdenacaoLeitura(0);
							}
						} else {
							leitura.setTarefaLeitura("9999");
							leitura.setOrdenacaoLeitura(0);
							leitura.setTarefaEntrega("9999");
						}
						if (tipoArquivo.substring(0, 2).equals("L1")) {
							leitura.setTipoLeitura("S");
						} else if (tipoArquivo.substring(0, 2).equals("RV") || tipoArquivo.substring(0, 2).equals("RR")) {
							leitura.setTipoLeitura("R");
						} else {
							leitura.setTipoLeitura("N");
						}
						if (linha.substring(329, 331).equals("02")) {
							percentual = 30;
						} else {
							if (leitura.getMedia3Meses() >= 0 && leitura.getMedia3Meses() < 2) {
								percentual = 600;
							} else if (leitura.getMedia3Meses() >= 2 && leitura.getMedia3Meses() < 3) {
								percentual = 500;
							} else if (leitura.getMedia3Meses() >= 3 && leitura.getMedia3Meses() < 4) {
								percentual = 400;
							} else if (leitura.getMedia3Meses() >= 4 && leitura.getMedia3Meses() < 5) {
								percentual = 300;
							} else if (leitura.getMedia3Meses() >= 5 && leitura.getMedia3Meses() < 6) {
								percentual = 200;
							} else if (leitura.getMedia3Meses() >= 6 && leitura.getMedia3Meses() < 11) {
								percentual = 100;
							} else if (leitura.getMedia3Meses() >= 11 && leitura.getMedia3Meses() < 20) {
								percentual = 90;
							} else {
								percentual = 70;
							}
						}
						leitura.setFaixaMinima(leitura.getUltimaLeitura() - ((leitura.getMedia3Meses() * percentual) / 100));
						if (leitura.getFaixaMinima() < 0) {
							leitura.setFaixaMinima(0);
						}
						leitura.setFaixaMaxima(leitura.getUltimaLeitura() + ((leitura.getMedia3Meses() * percentual) / 100)); 
						//this.leituraService.incluir(leitura);
						listaLeitura.add(leitura);
					}
					
				}
			}

			//DaoFactory.getLeituraDao().semTarefa(idImportacao); // => DESCOMENTAR
			this.leituraService.saveAll(listaLeitura);
			importacaoSalva.setObservacao("importado com sucesso!");
			importacaoSalva.setQtdImportacao(qtdImportada);
			this.importacaoRepository.save(importacaoSalva);
		} catch (Exception e) {
			importacaoSalva.setObservacao("ERRO: " + importacaoSalva.getId() + ", Linha: " + qtdImportada + ", inst: " + instalacao);
			importacaoSalva.setQtdImportacao(0);
			importacaoSalva.setNome(importacaoSalva.getNome() + "_ERRO");
			this.importacaoRepository.save(importacaoSalva);
			System.out.println("Causa: " + e.getCause());
			System.out.println("Mensagem Localizada " + e.getLocalizedMessage());
			System.out.println("Mensagem " + e.getMessage());
			return false;
		}
		LocalDateTime calFim = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
		System.out.println("Data Final Importacao: " + calFim + " - qtd: " + qtdImportada);
		return true;
	}
	
	public LocalDate formataDataBR(String dt) throws Exception {
		if (dt.substring(0, 2).equals("  ")) {
			return null;
		}
		String dia = dt.substring(0, 2);
		String mes = dt.substring(2, 4);
		String ano = dt.substring(4, 8);
		String data = ano + "-" + mes + "-" + dia; 
		LocalDate dt1 = LocalDate.parse(data);
		return dt1;
	}

}
