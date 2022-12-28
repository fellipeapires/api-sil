package br.com.sil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sil.model.Empresa;
import br.com.sil.repository.empresa.EmpresaRepositoryQuery;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>, EmpresaRepositoryQuery {

}
