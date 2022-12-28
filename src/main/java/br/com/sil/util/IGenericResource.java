package br.com.sil.util;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;

public interface IGenericResource<T, F, id extends Serializable> {

	public ResponseEntity<?> incluir(T entity);

	public ResponseEntity<?> alterar(T entity);

	public ResponseEntity<?> buscarPorId(long id);

	public ResponseEntity<?> pesquisar(F filter);

}
