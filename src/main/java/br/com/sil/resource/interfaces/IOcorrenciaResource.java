package br.com.sil.resource.interfaces;

import java.io.Serializable;

import br.com.sil.model.Ocorrencia;
import br.com.sil.repository.filter.OcorrenciaFilter;
import br.com.sil.util.IGenericResource;

public interface IOcorrenciaResource extends IGenericResource<Ocorrencia, OcorrenciaFilter, Serializable> {

}
