package br.com.sil.repository.retornoleitura;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sil.model.Operador;
import br.com.sil.model.RetornoLeitura;
import br.com.sil.model.RetornoLeitura_;
import br.com.sil.repository.filter.RetornoLeituraFilter;

public class RetornoLeituraRepositoryImpl implements RetornoLeituraRepositoryQuery {
	
	@Autowired
	private EntityManager manager;

	@Override
	public List<RetornoLeitura> pesquisar(RetornoLeituraFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<RetornoLeitura> criteria = builder.createQuery(RetornoLeitura.class);
		Root<RetornoLeitura> root = criteria.from(RetornoLeitura.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		criteria.orderBy(builder.desc(root.get(RetornoLeitura_.variacaoLeitura)));
		//criteria.orderBy(builder.desc(root.get(RetornoLeitura_.dataLeitura)));
		
		TypedQuery<RetornoLeitura> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(RetornoLeituraFilter filter, CriteriaBuilder builder, Root<RetornoLeitura> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (filter.getId() > 0) {
			predicates.add(builder.equal(root.get(RetornoLeitura_.id), filter.getId()));
		}
		if(filter.getIdLeitura() > 0) {
			predicates.add(builder.equal(root.join(RetornoLeitura_.leitura).<Long>get("id"), filter.getIdLeitura()));
		}
		if(filter.getIdUsuario() > 0) {
			predicates.add(builder.equal(root.join(RetornoLeitura_.usuario).<Long>get("id"), filter.getIdUsuario()));
		}
		if(filter.getOcorrencia() > 0) {
			predicates.add(builder.equal(root.join(RetornoLeitura_.ocorrencia).<Integer>get("codigo"), filter.getOcorrencia()));
		}
		if(filter.getIdRegional() > 0) {
			predicates.add(builder.equal(root.join(RetornoLeitura_.regional).<Long>get("id"), filter.getIdRegional()));
		}
		if (filter.getDataReferencia() != null) {
			predicates.add(builder.equal(root.get(RetornoLeitura_.dataReferencia), filter.getDataReferencia()));
		}
		if (filter.getGrupoFaturamento() > 0) {
			predicates.add(builder.equal(root.join(RetornoLeitura_.grupoFaturamento).<Integer>get("codigo"), filter.getGrupoFaturamento()));
		}
		if (filter.getTarefa() != null) {
			predicates.add(builder.equal(root.get(RetornoLeitura_.tarefaLeitura), filter.getTarefa()));
		}
		if (filter.getInstalacao() != null) {
			predicates.add(builder.equal(root.get(RetornoLeitura_.instalacao), filter.getInstalacao()));
		}
		if (filter.getMedidor() != null) {
			predicates.add(builder.equal(root.get(RetornoLeitura_.medidor), filter.getMedidor()));
		}
		if (filter.getMsgMobile() != null) {
			predicates.add(builder.equal(root.get(RetornoLeitura_.leitura).<String>get("mensAvisoMobile"), filter.getMsgMobile()));
		}
		if (filter.getSegmento() != null) {
			predicates.add(builder.equal(root.get(RetornoLeitura_.leitura).<String>get("codigoSeguimento"), filter.getSegmento()));
		}
		if (filter.getOperador() == Operador.MENOR_IGUAL.getCodigo()) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(RetornoLeitura_.variacaoLeitura), 0.0));		
			predicates.add(builder.lessThanOrEqualTo(root.get(RetornoLeitura_.variacaoLeitura), filter.getVariacaoLeitura()));			
		}
		if (filter.getOperador() == Operador.MAIOR_IGUAL.getCodigo()) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(RetornoLeitura_.variacaoLeitura), filter.getVariacaoLeitura()));	
		}
		if (filter.getTipoOcorrencia() != null) {
			predicates.add(builder.equal(root.get(RetornoLeitura_.ocorrencia).<Integer>get("tipoOcorrencia"), filter.getTipoOcorrencia()));
		}
		if (filter.getIsFoto() != null) {
			predicates.add(builder.equal(root.get(RetornoLeitura_.isFoto), filter.getIsFoto()));
		}
		if (filter.getFlagCritica() != null) {
			predicates.add(builder.equal(root.get(RetornoLeitura_.flagCritica), filter.getFlagCritica()));
		}
		predicates.add(builder.equal(root.get(RetornoLeitura_.ativo), filter.getAtivo()));
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
