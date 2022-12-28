package br.com.sil.service.interfaces;

import java.io.Serializable;

import br.com.sil.model.Ocorrencia;
import br.com.sil.repository.filter.OcorrenciaFilter;
import br.com.sil.util.IGenericService;

public interface IOcorrenciaService extends IGenericService<Ocorrencia, OcorrenciaFilter, Serializable> {

}
