package br.com.sil.repository.importacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sil.model.Importacao;
import br.com.sil.model.Importacao_;
import br.com.sil.repository.filter.ImportacaoFilter;

public class ImportacaoRepositoryImpl implements ImportacaoRepositoryQuery {

	@Autowired
	private EntityManager manager;

	@Override
	public List<Importacao> pesquisar(ImportacaoFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Importacao> criteria = builder.createQuery(Importacao.class);
		Root<Importacao> root = criteria.from(Importacao.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		TypedQuery<Importacao> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(ImportacaoFilter filter, CriteriaBuilder builder, Root<Importacao> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (filter.getId() > 0) {
			predicates.add(builder.equal(root.get(Importacao_.id), filter.getId()));
		}
		if (filter.getDataReferencia() != null) {
			predicates.add(builder.equal(root.get(Importacao_.dataReferencia), filter.getDataReferencia()));
		}
		if (filter.getIdRegional() != null) {
			predicates.add(builder.equal(root.join(Importacao_.regional).<Long>get("id"), filter.getIdRegional()));
		}
		if (filter.getIdGrupoFaturamento() != null) {
			predicates.add(builder.equal(root.join(Importacao_.grupoFaturamento).<Long>get("id"), filter.getIdGrupoFaturamento()));
		}
		if (filter.getIdUsuario() != null) {
			predicates.add(builder.equal(root.join(Importacao_.usuario).<Long>get("id"), filter.getIdUsuario()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
