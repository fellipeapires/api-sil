package br.com.sil.repository.regional;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sil.model.Regional;
import br.com.sil.model.Regional_;
import br.com.sil.repository.filter.RegionalFilter;


public class RegionalRepositoryImpl implements RegionalRepositoryQuery {
	@Autowired
	private EntityManager manager;

	@Override
	public List<Regional> pesquisar(RegionalFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Regional> criteria = builder.createQuery(Regional.class);
		Root<Regional> root = criteria.from(Regional.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		TypedQuery<Regional> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(RegionalFilter filter, CriteriaBuilder builder,
			Root<Regional> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (filter.getId() > 0) {
			predicates.add(builder.equal(root.get(Regional_.id), filter.getId()));
		}

		predicates.add(builder.equal(root.get(Regional_.situacao), filter.getSituacao()));

		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
