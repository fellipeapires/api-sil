package br.com.sil.resource.interfaces;

import java.io.Serializable;

import br.com.sil.model.RetornoFoto;
import br.com.sil.repository.filter.RetornoFotoFilter;
import br.com.sil.util.IGenericResource;

public interface IRetornoFotoResource extends IGenericResource<RetornoFoto, RetornoFotoFilter, Serializable> {

}
