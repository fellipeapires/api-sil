package br.com.sil.repository.exportacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sil.model.Exportacao;
import br.com.sil.model.Exportacao_;
import br.com.sil.repository.filter.ExportacaoFilter;

public class ExportacaoRepositoryImpl implements ExportacaoRepositoryQuery {

	@Autowired
	private EntityManager manager;

	@Override
	public List<Exportacao> pesquisar(ExportacaoFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Exportacao> criteria = builder.createQuery(Exportacao.class);
		Root<Exportacao> root = criteria.from(Exportacao.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		TypedQuery<Exportacao> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(ExportacaoFilter filter, CriteriaBuilder builder,
			Root<Exportacao> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (filter.getId() > 0) {
			predicates.add(builder.equal(root.get(Exportacao_.id), filter.getId()));
		}
		if (filter.getDataReferencia() != null) {
			predicates.add(builder.equal(root.get(Exportacao_.dataReferencia), filter.getDataReferencia()));
		}
		if (filter.getIdRegional() != null) {
			predicates.add(builder.equal(root.join(Exportacao_.regional).<Long>get("id"), filter.getIdRegional()));
		}
		if (filter.getIdGrupoFaturamento() != null) {
			predicates.add(builder.equal(root.join(Exportacao_.grupoFaturamento).<Long>get("id"), filter.getIdGrupoFaturamento()));
		}
		if (filter.getIdUsuario() != null) {
			predicates.add(builder.equal(root.join(Exportacao_.usuario).<Long>get("id"), filter.getIdUsuario()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
