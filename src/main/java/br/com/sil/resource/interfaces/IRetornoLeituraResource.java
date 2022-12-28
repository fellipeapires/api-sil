package br.com.sil.resource.interfaces;

import java.io.Serializable;

import br.com.sil.model.RetornoLeitura;
import br.com.sil.repository.filter.RetornoLeituraFilter;
import br.com.sil.util.IGenericResource;

public interface IRetornoLeituraResource extends IGenericResource<RetornoLeitura, RetornoLeituraFilter, Serializable> {

}
