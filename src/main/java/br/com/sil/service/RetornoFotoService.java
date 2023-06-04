package br.com.sil.service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
//import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import br.com.sil.config.ApiProperty;
import br.com.sil.model.DescricaoLog;
import br.com.sil.model.Leitura;
import br.com.sil.model.RetornoFoto;
import br.com.sil.model.Usuario;
import br.com.sil.model.dto.RetornoFotoDto;
import br.com.sil.repository.RetornoFotoRepository;
import br.com.sil.repository.filter.RetornoFotoFilter;
import br.com.sil.repository.projection.RetornoFotoProjection;
import br.com.sil.service.interfaces.IRetornoFotoService;
import br.com.sil.util.Utility;

@Service
public class RetornoFotoService implements IRetornoFotoService {
	
	@Autowired
	private ApiProperty apiProperty;
	
	@Autowired
	private RetornoFotoRepository retornoFotoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LeituraService leituraService;
	
	@Autowired
	private Utility utility; 

	@Override
	public RetornoFoto incluir(RetornoFoto entity) {
		return this.retornoFotoRepository.save(entity);
	}
	
	public int incluirRepasse(long idLeituraRepasse, long idUsuario, long idLeituraPasse) {
		return this.retornoFotoRepository.incluirRepasse(idLeituraRepasse, idUsuario, idLeituraPasse);
	}

	@Override
	public RetornoFoto alterar(RetornoFoto entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RetornoFoto buscarPorId(long id) {
		return this.retornoFotoRepository.findById(id).get();
	}
	
	@Override
	public List<RetornoFoto> pesquisar(RetornoFotoFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<RetornoFotoProjection> listar(Long idLeitura) {
		return this.retornoFotoRepository.listar(idLeitura);
	}
	
	public Optional<RetornoFoto> findByNome(String nome) {
		return this.retornoFotoRepository.findByNome(nome);
	}
	
	public List<String> getPath(long idRetornoFoto) {
		return this.retornoFotoRepository.getPath(idRetornoFoto);
	}

	public List<String> upload(List<RetornoFoto> listaFoto, long idUsuarioAlteracao) throws Exception {
		List<String> sync = new ArrayList<>();
		SimpleDateFormat formataData = new SimpleDateFormat("yyyyMMdd");
		String pasta = formataData.format(new Date());
		String imagem;
		String caminho = this.apiProperty.getPathFoto() + pasta;
		File novoDiretorio = new File(caminho);
		if (!novoDiretorio.exists()) {
			novoDiretorio.mkdir();
		}
		for (RetornoFoto photo : listaFoto) {
			try {
				Usuario usuario = this.usuarioService.buscarPorId(photo.getUsuario().getId());
				Leitura leitura = this.leituraService.buscarPorId(photo.getLeitura().getId());
				RetornoFoto retornoFoto = new RetornoFoto();
				String tipoFoto = photo.getImagem().substring(0, 22);
				if (tipoFoto.equalsIgnoreCase("data:image/png;base64,")) {
					imagem = photo.getImagem().replace("data:image/png;base64,", "data:image/jpg;base64,");
				} else if (tipoFoto.equalsIgnoreCase("data:image/jpeg;base64,")) {
					imagem = photo.getImagem().replace("data:image/jpeg;base64,", "data:image/jpg;base64,");
				} else {
					imagem = photo.getImagem();
				}
				byte[] image = Base64.decodeBase64(imagem.replace("data:image/jpg;base64,", ""));
				retornoFoto.setInstalacao(photo.getInstalacao());
				retornoFoto.setMedidor(photo.getMedidor());
				retornoFoto.setNome(photo.getNome());
				ArrayByteToImagem(image, caminho + "/" + retornoFoto.getNome());
				retornoFoto.setPath(caminho + "/" + retornoFoto.getNome());
				retornoFoto.setDataFoto(LocalDateTime.parse(photo.getDataFoto().toString()));
				retornoFoto.setLatitude(photo.getLatitude());
				retornoFoto.setLongitude(photo.getLongitude());
				retornoFoto.setMarca(photo.getMarca());
				retornoFoto.setModelo(photo.getModelo());
				retornoFoto.setImagem("");
				retornoFoto.setUsuario(usuario);
				retornoFoto.setLeitura(leitura);
				sync.add(retornoFoto.getNome());
				RetornoFoto foto = this.retornoFotoRepository.isExist(retornoFoto.getNome());
				if (foto != null) {
					retornoFoto.setId(foto.getId());
					retornoFoto.setNome(foto.getNome());
					ArrayByteToImagem(image, caminho + "/" + foto.getNome());
					retornoFoto.setPath(caminho + "/" + foto.getNome());
					retornoFoto.setDataFoto(foto.getDataFoto());
					if (retornoFoto.getLatitude().equals("0")) {
						retornoFoto.setLatitude(foto.getLatitude());
					}
					if (retornoFoto.getLongitude().equals("0")) {
						retornoFoto.setLongitude(foto.getLongitude());
					}
				}
				RetornoFoto f = retornoFotoRepository.save(retornoFoto);
				if (f != null) {
					this.retornoFotoRepository.setLogIncluirFoto(f.getId(), idUsuarioAlteracao, DescricaoLog.INCLUSAO_FOTO.getNome(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
				}
			} catch (Exception e) {
				System.out.println("CAUSA ERRO: " + e.getMessage() + " - idUsuario: " + photo.getUsuario().getId() + " - nomeFoto: " + photo.getNome());
			}
		}
		return sync;
	}
	
	public List<String> uploadMobile(List<RetornoFotoDto> listaFoto) throws Exception {
		List<String> sync = new ArrayList<>();
		SimpleDateFormat formataData = new SimpleDateFormat("yyyyMMdd");
		String pasta = formataData.format(new Date());
		String imagem;
		String caminho = this.apiProperty.getPathFoto() + pasta;
		List<RetornoFoto> listaFotoSaveAll = new ArrayList<>();
		File novoDiretorio = new File(caminho);
		if (!novoDiretorio.exists()) {
			novoDiretorio.mkdir();
		}
		for (RetornoFotoDto photo : listaFoto) {
			try {
				Usuario usuario = this.usuarioService.buscarPorId(photo.getIdFuncionario());
				Leitura leitura = this.leituraService.buscarPorId(photo.getIdLeitura());
				RetornoFoto retornoFoto = new RetornoFoto();
				String tipoFoto = photo.getImagem().substring(0, 22).replace(",", "");
				if (tipoFoto.equalsIgnoreCase("data:image/png;base64")) {
					imagem = photo.getImagem().replace("data:image/png;base64,", "data:image/jpg;base64,");
				} else if (tipoFoto.equalsIgnoreCase("data:image/jpeg;base64")) {
					imagem = photo.getImagem().replace("data:image/jpeg;base64,", "data:image/jpg;base64,");
				} else {
					imagem = photo.getImagem();
				}
				byte[] image = Base64.decodeBase64(imagem.replace("data:image/jpg;base64,", ""));
				retornoFoto.setInstalacao(photo.getInstalacao());
				retornoFoto.setMedidor(leitura.getMedidor());
				retornoFoto.setNome(photo.getNomeFoto());
				ArrayByteToImagem(image, caminho + "/" + retornoFoto.getNome());
				retornoFoto.setPath(caminho + "/" + retornoFoto.getNome());
				retornoFoto.setDataFoto(LocalDateTime.parse(photo.getDataFoto().toString()));
				retornoFoto.setLatitude(photo.getLatitude());
				retornoFoto.setLongitude(photo.getLongitude());
				retornoFoto.setMarca("");
				retornoFoto.setModelo("");
				retornoFoto.setImagem("");
				retornoFoto.setUsuario(usuario);
				retornoFoto.setLeitura(leitura);
				sync.add(retornoFoto.getNome());
				RetornoFoto foto = this.retornoFotoRepository.isExist(retornoFoto.getNome());
				if (foto != null) {
					retornoFoto.setId(foto.getId());
					retornoFoto.setNome(foto.getNome());
					ArrayByteToImagem(image, caminho + "/" + foto.getNome());
					retornoFoto.setPath(caminho + "/" + foto.getNome());
					retornoFoto.setDataFoto(foto.getDataFoto());
					if (retornoFoto.getLatitude().equals("0")) {
						retornoFoto.setLatitude(foto.getLatitude());
					}
					if (retornoFoto.getLongitude().equals("0")) {
						retornoFoto.setLongitude(foto.getLongitude());
					}
				}
				listaFotoSaveAll.add(retornoFoto);
			} catch (Exception e) {
				System.out.println("CAUSA ERRO: " + e.getMessage());
			}
		}
		retornoFotoRepository.saveAll(listaFotoSaveAll);
		return sync;
	}

	private String ArrayByteToImagem(byte[] stream, String pathFull) throws Exception {
		FileOutputStream tmpImagem = new FileOutputStream(pathFull);
		tmpImagem.write(stream);
		tmpImagem.close();
		return "ok";
	}
	
	public HttpEntity<byte[]> baixarZipImagens(long idRetornoFoto, long idUsuarioAlteracao) throws Exception {
		try {
			List<String> listaPath = this.getPath(idRetornoFoto);

			String arquivoZip = listaPath.get(0).substring(0, listaPath.get(0).length() - 4) + ".zip";
			this.utility.compactarArquivoMult(listaPath, arquivoZip);

			SimpleDateFormat formataData = new SimpleDateFormat("yyyyMMddHHmmss");
			String nomeZip = formataData.format(new Date());

			String path = arquivoZip;
			byte[] arquivo = Files.readAllBytes(Paths.get(path));
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Content-Disposition", "attachment;filename=imagem_" + nomeZip + ".zip");
			HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);
			if  (entity != null) {
				this.retornoFotoRepository.setLogIncluirFoto(idRetornoFoto, idUsuarioAlteracao, DescricaoLog.DOWNLOAD_FOTO.getNome(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
			}
			return entity;	
		} catch (Exception e) {
			System.out.println("erro no download foto: " + e.getMessage());
			return null;
		}
	}
	

}
