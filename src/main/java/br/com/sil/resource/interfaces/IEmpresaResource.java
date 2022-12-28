package br.com.sil.resource.interfaces;

import java.io.Serializable;

import br.com.sil.model.Empresa;
import br.com.sil.repository.filter.EmpresaFilter;
import br.com.sil.util.IGenericResource;

public interface IEmpresaResource extends IGenericResource<Empresa, EmpresaFilter, Serializable> {

}
