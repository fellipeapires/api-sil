package br.com.sil.repository.grupofaturamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sil.model.GrupoFaturamento;
import br.com.sil.model.GrupoFaturamento_;
import br.com.sil.repository.filter.GrupoFaturamentoFilter;

public class GrupoFaturamentoRepositoryImpl implements GrupoFaturamentoRepositoryQuery {

	@Autowired
	private EntityManager manager;

	@Override
	public List<GrupoFaturamento> pesquisar(GrupoFaturamentoFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<GrupoFaturamento> criteria = builder.createQuery(GrupoFaturamento.class);
		Root<GrupoFaturamento> root = criteria.from(GrupoFaturamento.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		TypedQuery<GrupoFaturamento> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(GrupoFaturamentoFilter filter, CriteriaBuilder builder,
			Root<GrupoFaturamento> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (filter.getId() > 0) {
			predicates.add(builder.equal(root.get(GrupoFaturamento_.id), filter.getId()));
		}
		if (filter.getCodigo() != null) {
			predicates.add(builder.equal(root.get(GrupoFaturamento_.codigo), filter.getCodigo()));
		}

		predicates.add(builder.equal(root.get(GrupoFaturamento_.situacao), filter.getSituacao()));

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
