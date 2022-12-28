package br.com.sil.service.interfaces;

import java.io.Serializable;

import br.com.sil.model.PerfilAcesso;
import br.com.sil.repository.filter.PerfilAcessoFilter;
import br.com.sil.util.IGenericService;


public interface IPerfilAcessoService extends IGenericService<PerfilAcesso, PerfilAcessoFilter, Serializable> {

}
