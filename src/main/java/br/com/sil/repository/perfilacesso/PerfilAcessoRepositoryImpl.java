package br.com.sil.repository.perfilacesso;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

//import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.sil.model.PerfilAcesso;
import br.com.sil.model.PerfilAcesso_;
import br.com.sil.repository.filter.PerfilAcessoFilter;

public class PerfilAcessoRepositoryImpl implements PerfilAcessoRepositoryQuery {
	
	@Autowired
	private EntityManager manager;

	@Override
	public List<PerfilAcesso> pesquisar(PerfilAcessoFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<PerfilAcesso> criteria = builder.createQuery(PerfilAcesso.class);
		Root<PerfilAcesso> root = criteria.from(PerfilAcesso.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		TypedQuery<PerfilAcesso> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(PerfilAcessoFilter filter, CriteriaBuilder builder,
			Root<PerfilAcesso> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (filter.getId() > 0) {
			predicates.add(builder.equal(root.get(PerfilAcesso_.id), filter.getId()));
		}

		predicates.add(builder.equal(root.get(PerfilAcesso_.situacao), filter.getSituacao()));

		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
