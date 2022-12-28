package br.com.sil.resource.interfaces;

import java.io.Serializable;

import br.com.sil.model.Regional;
import br.com.sil.repository.filter.RegionalFilter;
import br.com.sil.util.IGenericResource;

public interface IRegionalResource extends IGenericResource<Regional, RegionalFilter, Serializable> {

}
