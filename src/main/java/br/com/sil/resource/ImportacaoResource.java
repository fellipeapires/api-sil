package br.com.sil.resource;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.sil.config.ApiProperty;
import br.com.sil.model.Importacao;
import br.com.sil.repository.filter.ImportacaoFilter;
import br.com.sil.resource.interfaces.IImportacaoResource;
import br.com.sil.service.GrupoFaturamentoService;
import br.com.sil.service.ImportacaoService;

@RestController
@RequestMapping("/importacao")
public class ImportacaoResource implements IImportacaoResource {

	@Autowired
	private ImportacaoService importacaoService;
	
	@Autowired
	private GrupoFaturamentoService grupoFaturamentoService;
	
	@Autowired
	private ApiProperty apiProperty;

	@Override
	@PostMapping("/incluir")
	public ResponseEntity<Importacao> incluir(@RequestBody Importacao entity) {
		Importacao importacao = this.importacaoService.incluir(entity);
		return new ResponseEntity<Importacao>(importacao, HttpStatus.CREATED);
	}

	@Override
	@PutMapping("/alterar")
	public ResponseEntity<?> alterar(@RequestBody Importacao entity) {
		Importacao importacao = this.importacaoService.alterar(entity);
		return new ResponseEntity<Importacao>(importacao, HttpStatus.OK);
	}

	@Override
	@GetMapping("/buscarporid/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable("id") long id) {
		Importacao importacao = this.importacaoService.buscarPorId(id);
		return importacao != null ? new ResponseEntity<Importacao>(importacao, HttpStatus.OK)
				: ResponseEntity.notFound().build();
	}

	@Override
	@GetMapping("/pesquisar")
	public ResponseEntity<?> pesquisar(ImportacaoFilter filter) {
		List<Importacao> lista = this.importacaoService.pesquisar(filter);
		return new ResponseEntity<List<Importacao>>(lista, HttpStatus.OK);
	}

	@PostMapping("/importar/{idUsuario}/{idRegional}/{grupoFaturamento}/{dataReferencia}")
	public ResponseEntity<?> importar(MultipartFile arquivo, @PathVariable("idUsuario") Long idUsuario, @PathVariable("idRegional") Long idRegional,
			@PathVariable("grupoFaturamento") Integer grupoFaturamento, @PathVariable("dataReferencia") String dataReferencia) throws Exception {
		File diretorio = new File(apiProperty.getPathImportacao());
		byte[] bytes = arquivo.getBytes();
		Path path = Paths.get(diretorio + File.separator + arquivo.getOriginalFilename());
		Files.write(path, bytes);
		Boolean importado = false;
		if (grupoFaturamentoService.findByCodigo(grupoFaturamento).isPresent() && arquivo != null && idUsuario != null && idRegional != null && grupoFaturamento != null && dataReferencia != null ) {
			if (!this.importacaoService.findByNome(arquivo.getOriginalFilename()).isPresent()) {
				importado = this.importacaoService.importar(arquivo, idUsuario, idRegional, grupoFaturamento, dataReferencia);
			}
			if (importado) {
				return new ResponseEntity<Boolean>(importado, HttpStatus.OK);
			} else {
				//return new ResponseEntity<Boolean>(importado, HttpStatus.EXPECTATION_FAILED);
				return new ResponseEntity<Boolean>(importado, HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<Boolean>(importado, HttpStatus.BAD_REQUEST);
		}
	}

}
