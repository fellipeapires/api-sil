package br.com.sil.service.interfaces;

import java.io.Serializable;

import br.com.sil.model.Leitura;
import br.com.sil.repository.filter.LeituraFilter;
import br.com.sil.util.IGenericService;

public interface ILeituraService extends IGenericService<Leitura, LeituraFilter, Serializable> {

}
