package br.com.sil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sil.model.Empresa;
import br.com.sil.repository.EmpresaRepository;
import br.com.sil.repository.filter.EmpresaFilter;
import br.com.sil.service.interfaces.IEmpresaService;

@Service
public class EmpresaService implements IEmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public Empresa incluir(Empresa entity) {
		return this.empresaRepository.save(entity);
	}

	@Override
	public Empresa alterar(Empresa entity) {
		return this.empresaRepository.save(entity);
	}

	@Override
	public Empresa buscarPorId(long id) {
		return this.empresaRepository.findById(id).get();
	}

	@Override
	public List<Empresa> pesquisar(EmpresaFilter filter) {
		return this.empresaRepository.pesquisar(filter);
	}
}
