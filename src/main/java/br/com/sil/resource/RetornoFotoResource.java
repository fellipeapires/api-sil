package br.com.sil.resource;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sil.config.ApiProperty;
import br.com.sil.model.RetornoFoto;
import br.com.sil.model.dto.RetornoFotoDto;
import br.com.sil.repository.filter.RetornoFotoFilter;
import br.com.sil.repository.projection.RetornoFotoProjection;
import br.com.sil.resource.interfaces.IRetornoFotoResource;
import br.com.sil.service.RetornoFotoService;


@RestController
@RequestMapping("/retornofoto")
public class RetornoFotoResource implements IRetornoFotoResource {
	
	@Autowired
	private ApiProperty apiProperty;
	
	@Autowired
	private RetornoFotoService retornoFotoService;
	
	@Override
	public ResponseEntity<?> incluir(RetornoFoto entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> alterar(RetornoFoto entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> buscarPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> pesquisar(RetornoFotoFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(RetornoFotoFilter filter) throws Exception {
		List<RetornoFotoProjection> lista = this.retornoFotoService.listar(filter);
		return new ResponseEntity<List<RetornoFotoProjection>>(lista, HttpStatus.OK);
	}

	@GetMapping("/getfoto/{caminho}")
	public void getFoto(@PathVariable("caminho") String caminho, HttpServletResponse response) throws Exception {
		try {
			String caminhoFoto = this.apiProperty.getPathFoto();
			String pathFoto = caminho.substring(0, 9).replace("-", "/") + caminho.substring(9, caminho.length());
			caminhoFoto += pathFoto;
			File imagem = new File(caminhoFoto);
			Files.copy(imagem.toPath(), response.getOutputStream());	
		} catch (Exception e) {
			System.out.println("erro ao buscar foto: " + e.getMessage());
		}
	}
	
	@PostMapping("/upload/{idUsuarioAlteracao}")
	public ResponseEntity<?> upload(@RequestBody List<RetornoFoto> listaFoto, @PathVariable("idUsuarioAlteracao") long idUsuarioAlteracao) throws Exception {
		List<String> lista = this.retornoFotoService.upload(listaFoto, idUsuarioAlteracao);
		return new ResponseEntity<List<String>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/downloadphotozip/{idRetornoFoto}/{idUsuarioAlteracao}")
	@Produces("application/zip")
	public HttpEntity<byte[]> baixarZipImagens(@PathVariable("idRetornoFoto") long idRetornoFoto, @PathVariable("idUsuarioAlteracao") long idUsuarioAlteracao) throws Exception {
		return this.retornoFotoService.baixarZipImagens(idRetornoFoto, idUsuarioAlteracao);
	}
	
	@PostMapping("/uploadmobile")
	public ResponseEntity<?> uploadMobile(@RequestBody List<RetornoFotoDto> listaFoto) throws Exception {
		List<String> lista = this.retornoFotoService.uploadMobile(listaFoto);
		return new ResponseEntity<List<String>>(lista, HttpStatus.OK);
	}

}
