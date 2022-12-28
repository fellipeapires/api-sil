package br.com.sil.resource;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sil.config.ApiProperty;
import br.com.sil.model.Exportacao;
import br.com.sil.repository.filter.ExportacaoFilter;
import br.com.sil.resource.interfaces.IExportacaoResource;
import br.com.sil.service.ExportacaoService;

@RestController
@RequestMapping("/exportacao")
public class ExportacaoResource implements IExportacaoResource {
	
	@Autowired
	private ApiProperty apiProperty;
	
	@Autowired
	private ExportacaoService exportacaoService;

	@Override
	public ResponseEntity<?> incluir(Exportacao entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> alterar(Exportacao entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> buscarPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping("/pesquisar")
	public ResponseEntity<?> pesquisar(ExportacaoFilter filter) {
		List<Exportacao> lista = this.exportacaoService.pesquisar(filter);
		return new ResponseEntity<List<Exportacao>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/gerararquivo")
	public ResponseEntity<?> getGerarArquivo(ExportacaoFilter filter) throws Exception {
		Exportacao exportacao = this.exportacaoService.exportar(filter);
		return new ResponseEntity<Exportacao>(exportacao, HttpStatus.OK);
	}
	
	@GetMapping("/baixararquivo/{nomeArquivo}")
	@Produces("text/txt")
	public HttpEntity<byte[]> baixarArquivo(@PathVariable("nomeArquivo") String nomeArquivo) throws Exception {
		byte[] arquivo = Files.readAllBytes(Paths.get(this.apiProperty.getPathExportacao() + nomeArquivo));
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Disposition", "attachment;filename=" + nomeArquivo);
		HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);
		return entity;
	}

}
