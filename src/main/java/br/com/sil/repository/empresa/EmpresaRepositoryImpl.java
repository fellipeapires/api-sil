package br.com.sil.repository.empresa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sil.model.Empresa;
import br.com.sil.model.Empresa_;
import br.com.sil.repository.filter.EmpresaFilter;

public class EmpresaRepositoryImpl implements EmpresaRepositoryQuery {
	
	@Autowired
	private EntityManager manager;

	@Override
	public List<Empresa> pesquisar(EmpresaFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Empresa> criteria = builder.createQuery(Empresa.class);
		Root<Empresa> root = criteria.from(Empresa.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		TypedQuery<Empresa> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	private Predicate[] criarRestricoes(EmpresaFilter filter, CriteriaBuilder builder,
			Root<Empresa> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (filter.getId() > 0) {
			predicates.add(builder.equal(root.get(Empresa_.id), filter.getId()));
		}

		predicates.add(builder.equal(root.get(Empresa_.situacao), filter.getSituacao()));

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
