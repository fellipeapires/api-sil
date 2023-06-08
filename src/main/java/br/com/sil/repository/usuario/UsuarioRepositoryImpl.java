package br.com.sil.repository.usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.sil.model.StatusRegitro;
import br.com.sil.model.Usuario;
import br.com.sil.model.Usuario_;
import br.com.sil.repository.filter.UsuarioFilter;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery {

	@Autowired
	private EntityManager manager;

	@Override
	public List<Usuario> pesquisar(UsuarioFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		TypedQuery<Usuario> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(UsuarioFilter filter, CriteriaBuilder builder, Root<Usuario> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (filter.getNome() != null) {
			predicates.add(builder.like(builder.lower(root.get(Usuario_.nome)), filter.getNome() + "%"));
		}
		if (filter.getMatricula() != null) {
			predicates.add(builder.equal(builder.lower(root.get(Usuario_.matricula)), filter.getMatricula()));
		}
		if (filter.getLogin() != null) {
			predicates.add(builder.like(builder.lower(root.get(Usuario_.login)), filter.getLogin() + "%"));
		}
		if(filter.getIdRegional() > 0) {
			predicates.add(builder.equal(root.join(Usuario_.regionais).<Long>get("id"), filter.getIdRegional()));
		}
		if (filter.getIdPerfilAcesso() > 0) {
			predicates.add(builder.equal(root.join(Usuario_.perfilAcesso).<Long>get("id"), filter.getIdPerfilAcesso()));
		}
		if (filter.getSituacao() != StatusRegitro.TODOS.getCodigo()) {			
			predicates.add(builder.equal(root.get(Usuario_.situacao), filter.getSituacao()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
