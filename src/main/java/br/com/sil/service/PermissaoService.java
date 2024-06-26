package br.com.sil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sil.model.Permissao;
import br.com.sil.model.dto.PermissaoDto;
import br.com.sil.repository.PermissaoRepository;
import br.com.sil.repository.filter.PermissaoFilter;
import br.com.sil.service.interfaces.IPermissaoService;

@Service
public class PermissaoService implements IPermissaoService {
	
	@Autowired
	private PermissaoRepository permissaoRepository;

	@Override
	public Permissao incluir(Permissao entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permissao alterar(Permissao entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permissao buscarPorId(long id) {
		return this.permissaoRepository.findById(id).get();
	}

	@Override
	public List<Permissao> pesquisar(PermissaoFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Optional<Permissao> findByNome(String nome) {
		return this.permissaoRepository.findByNome(nome);
	}
	
	public List<Permissao> getPermissoesCrudUser() {
		return this.permissaoRepository.getPermissoesCrudUser();
	}
	
	public List<Permissao> getPermissoesAssociadasUsuario(PermissaoFilter filter) {
		return this.permissaoRepository.getPermissoesAssociadasUsuario(filter.getIdUsuario());
	}
	
	public List<Permissao> getPermissoesNaoAssociadasUsuario(PermissaoFilter filter) {
		return this.permissaoRepository.getPermissoesNaoAssociadasUsuario(filter.getIdUsuario());
	}
	
	public int incluirPermissaoUsuario(PermissaoDto entity) {
		return this.permissaoRepository.incluirPermissaoUsuario(entity.getIdUsuario(), entity.getListaIdPermissao());
	}
	
	public int removerPermissaoUsuario(PermissaoDto entity) {
		return this.permissaoRepository.removerPermissaoUsuario(entity.getIdUsuario(), entity.getListaIdPermissao());
	}

}
