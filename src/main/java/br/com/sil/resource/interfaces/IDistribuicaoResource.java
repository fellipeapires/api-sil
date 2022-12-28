package br.com.sil.resource.interfaces;

import java.io.Serializable;

import br.com.sil.model.Distribuicao;
import br.com.sil.repository.filter.DistribuicaoFilter;
import br.com.sil.util.IGenericResource;

public interface IDistribuicaoResource extends IGenericResource<Distribuicao, DistribuicaoFilter, Serializable> {

}
