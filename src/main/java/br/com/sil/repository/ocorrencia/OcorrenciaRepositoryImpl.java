package br.com.sil.repository.ocorrencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sil.model.Ocorrencia;
import br.com.sil.model.Ocorrencia_;
import br.com.sil.repository.filter.OcorrenciaFilter;

public class OcorrenciaRepositoryImpl implements OcorrenciaRepositoryQuery {

	@Autowired
	private EntityManager manager;

	@Override
	public List<Ocorrencia> pesquisar(OcorrenciaFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Ocorrencia> criteria = builder.createQuery(Ocorrencia.class);
		Root<Ocorrencia> root = criteria.from(Ocorrencia.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		TypedQuery<Ocorrencia> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(OcorrenciaFilter filter, CriteriaBuilder builder,
			Root<Ocorrencia> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (filter.getId() > 0) {
			predicates.add(builder.equal(root.get(Ocorrencia_.id), filter.getId()));
		}
		if (filter.getCodigo() != null) {
			predicates.add(builder.equal(root.get(Ocorrencia_.codigo), filter.getCodigo()));
		}

		predicates.add(builder.equal(root.get(Ocorrencia_.situacao), filter.getSituacao()));

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
