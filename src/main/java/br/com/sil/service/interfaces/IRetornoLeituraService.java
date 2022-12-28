package br.com.sil.service.interfaces;

import java.io.Serializable;

import br.com.sil.model.RetornoLeitura;
import br.com.sil.repository.filter.RetornoLeituraFilter;
import br.com.sil.util.IGenericService;

public interface IRetornoLeituraService extends IGenericService<RetornoLeitura, RetornoLeituraFilter, Serializable> {

}
