package br.com.sil.service.interfaces;

import java.io.Serializable;

import br.com.sil.model.RetornoFoto;
import br.com.sil.repository.filter.RetornoFotoFilter;
import br.com.sil.util.IGenericService;

public interface IRetornoFotoService extends IGenericService<RetornoFoto, RetornoFotoFilter, Serializable> {

}
