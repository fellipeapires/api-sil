package br.com.sil.repository.leitura;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.sil.model.Leitura;
import br.com.sil.model.Leitura_;
import br.com.sil.repository.filter.LeituraFilter;

public class LeituraRepositoryImpl implements LeituraRepositoryQuery {

	@Autowired
	private EntityManager manager;

	@Override
	public Page<Leitura> pesquisar(LeituraFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Leitura> criteria = builder.createQuery(Leitura.class);
		Root<Leitura> root = criteria.from(Leitura.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		//criteria.orderBy(builder.asc(root.get(Leitura_.endereco)));
		criteria.orderBy(builder.asc(root.get(Leitura_.tarefaLeitura)));

		TypedQuery<Leitura> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}

	private Predicate[] criarRestricoes(LeituraFilter filter, CriteriaBuilder builder, Root<Leitura> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (filter.getId() > 0) {
			predicates.add(builder.equal(root.get(Leitura_.id), filter.getId()));
		}
		if (filter.getIdImportacao() > 0) {
			predicates.add(builder.equal(root.join(Leitura_.importacao).<Long>get("id"), filter.getIdImportacao()));
		}
		if(filter.getIdRegional() > 0) {
			predicates.add(builder.equal(root.join(Leitura_.importacao).<Long>get("regional"), filter.getIdRegional()));
		}
		if (filter.getDataReferencia() != null) {
			predicates.add(builder.equal(root.get(Leitura_.importacao).<LocalDate>get("dataReferencia"), filter.getDataReferencia()));
		}
		if (filter.getGrupoFaturamento() > 0) {
			predicates.add(builder.equal(root.join(Leitura_.grupoFaturamento).<Integer>get("codigo"), filter.getGrupoFaturamento()));
		}
		if (filter.getTarefa() != null) {
			predicates.add(builder.equal(root.get(Leitura_.tarefaLeitura), filter.getTarefa()));
		}
		if (filter.getTarefaEntrega() != null) {
			predicates.add(builder.equal(root.get(Leitura_.tarefaEntrega), filter.getTarefaEntrega()));
		}
		if (filter.getInstalacao() != null) {
			predicates.add(builder.like(builder.upper(root.get(Leitura_.instalacao)), "%" + filter.getInstalacao()));
		}
		if (filter.getMedidor() != null) {
			predicates.add(builder.like(builder.upper(root.get(Leitura_.medidor)), "%" + filter.getMedidor()));
		}
		if (filter.getEndereco() != null) {
			predicates.add(builder.like(builder.upper(root.get(Leitura_.endereco)), filter.getEndereco().trim() + "%"));
		}
		if (filter.getCep() != null) {
			predicates.add(builder.like(builder.upper(root.get(Leitura_.cep)), filter.getCep() + "%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(LeituraFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Leitura> root = criteria.from(Leitura.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
