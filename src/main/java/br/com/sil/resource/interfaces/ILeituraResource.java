package br.com.sil.resource.interfaces;

import java.io.Serializable;

import br.com.sil.model.Leitura;
import br.com.sil.repository.filter.LeituraFilter;
import br.com.sil.util.IGenericResource;

public interface ILeituraResource extends IGenericResource<Leitura, LeituraFilter, Serializable> {

}
