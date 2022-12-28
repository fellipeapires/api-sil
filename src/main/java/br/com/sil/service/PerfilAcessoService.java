package br.com.sil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sil.model.PerfilAcesso;
import br.com.sil.repository.PerfilAcessoRepository;
import br.com.sil.repository.filter.PerfilAcessoFilter;
import br.com.sil.service.interfaces.IPerfilAcessoService;

@Service
public class PerfilAcessoService implements IPerfilAcessoService {
	
	@Autowired
	private PerfilAcessoRepository perfilAcessoRepository;

	@Override
	public PerfilAcesso incluir(PerfilAcesso entity) {
		return this.perfilAcessoRepository.save(entity);
	}

	@Override
	public PerfilAcesso alterar(PerfilAcesso entity) {
		return this.perfilAcessoRepository.save(entity);
	}

	@Override
	public PerfilAcesso buscarPorId(long id) {
		return this.perfilAcessoRepository.findById(id).get();
	}

	@Override
	public List<PerfilAcesso> pesquisar(PerfilAcessoFilter filter) {
		return this.perfilAcessoRepository.pesquisar(filter);
	}
	
	public List<PerfilAcesso> getPerfilPorUsuario(long idUsuario) {
		return this.perfilAcessoRepository.getPerfilPorUsuario(idUsuario);
	}

}
