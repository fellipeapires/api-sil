package br.com.sil.service.interfaces;

import java.io.Serializable;

import br.com.sil.model.Empresa;
import br.com.sil.repository.filter.EmpresaFilter;
import br.com.sil.util.IGenericService;

public interface IEmpresaService extends IGenericService<Empresa, EmpresaFilter, Serializable> {

}
