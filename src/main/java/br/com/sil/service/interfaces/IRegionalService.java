package br.com.sil.service.interfaces;

import java.io.Serializable;

import br.com.sil.model.Regional;
import br.com.sil.repository.filter.RegionalFilter;
import br.com.sil.util.IGenericService;

public interface IRegionalService extends IGenericService<Regional, RegionalFilter, Serializable> {

}
