package br.com.sil.service.interfaces;

import java.io.Serializable;

import br.com.sil.model.Distribuicao;
import br.com.sil.repository.filter.DistribuicaoFilter;
import br.com.sil.util.IGenericService;

public interface IDistribuicaoService extends IGenericService<Distribuicao, DistribuicaoFilter, Serializable> {

}
