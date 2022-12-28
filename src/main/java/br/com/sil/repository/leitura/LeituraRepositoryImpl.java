package br.com.sil.repository.leitura;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sil.model.Leitura;
import br.com.sil.model.Leitura_;
import br.com.sil.repository.filter.LeituraFilter;

public class LeituraRepositoryImpl implements LeituraRepositoryQuery {

	@Autowired
	private EntityManager manager;

	@Override
	public List<Leitura> pesquisar(LeituraFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Leitura> criteria = builder.createQuery(Leitura.class);
		Root<Leitura> root = criteria.from(Leitura.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		TypedQuery<Leitura> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(LeituraFilter filter, CriteriaBuilder builder, Root<Leitura> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (filter.getId() > 0) {
			predicates.add(builder.equal(root.get(Leitura_.id), filter.getId()));
		}
		if(filter.getIdImportacao() > 0) {
			predicates.add(builder.equal(root.join(Leitura_.importacao).<Long>get("id"), filter.getIdImportacao()));
		}
		
		if (filter.getGrupoFaturamento() > 0) {
			predicates.add(builder.equal(root.join(Leitura_.grupoFaturamento).<Integer>get("codigo"), filter.getGrupoFaturamento()));
		}
		if (filter.getTarefa() != null) {
			predicates.add(builder.equal(root.get(Leitura_.tarefaLeitura), filter.getTarefa()));
		}
		if (filter.getInstalacao() != null) {
			predicates.add(builder.equal(root.get(Leitura_.instalacao), filter.getInstalacao()));
		}
		if (filter.getMedidor() != null) {
			predicates.add(builder.equal(root.get(Leitura_.medidor), filter.getMedidor()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
