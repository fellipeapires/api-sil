package br.com.sil.repository.retornofoto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sil.model.RetornoFoto;
import br.com.sil.model.RetornoFoto_;
import br.com.sil.repository.filter.RetornoFotoFilter;

public class RetornoFotoRepositoryImpl implements RetornoFotoRepositoryQuery {
	
	@Autowired
	private EntityManager manager;

	@Override
	public List<RetornoFoto> pesquisar(RetornoFotoFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<RetornoFoto> criteria = builder.createQuery(RetornoFoto.class);
		Root<RetornoFoto> root = criteria.from(RetornoFoto.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		TypedQuery<RetornoFoto> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(RetornoFotoFilter filter, CriteriaBuilder builder, Root<RetornoFoto> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (filter.getId() > 0) {
			predicates.add(builder.equal(root.get(RetornoFoto_.id), filter.getId()));
		}
		if(filter.getIdLeitura() > 0) {
			predicates.add(builder.equal(root.join(RetornoFoto_.leitura).<Long>get("id"), filter.getIdLeitura()));
		}
		if(filter.getIdUsuario() > 0) {
			predicates.add(builder.equal(root.join(RetornoFoto_.usuario).<Long>get("id"), filter.getIdUsuario()));
		}
		if (filter.getDataInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(RetornoFoto_.dataFoto), filter.getDataInicio()));
		}
		if (filter.getDataFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(RetornoFoto_.dataFoto), filter.getDataFim()));
		}
		if (filter.getInstalacao() != null) {
			predicates.add(builder.equal(root.get(RetornoFoto_.instalacao), filter.getInstalacao()));
		}
		if (filter.getMedidor() != null) {
			predicates.add(builder.equal(root.get(RetornoFoto_.medidor), filter.getMedidor()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
