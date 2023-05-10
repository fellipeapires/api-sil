package br.com.sil.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sil.model.Permissao;
import br.com.sil.repository.permissao.PermissaoRepositoryQuery;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>, PermissaoRepositoryQuery {
	public Optional<Permissao> findByNome(String nome);
	
	@Transactional
	@Query(value="SELECT * FROM SEG_PERMISSAO WHERE DS_DESCRICAO IN("
			+ "'ROLE_ADMINISTRATIVO',"
			+ "'ROLE_PESQUISAR',"
			+ "'ROLE_LEITURA',"
			+ "'ROLE_RETORNO_LEITURA',"
			+ "'ROLE_PAINEL_CONFIGURACAO',"
			+ "'ROLE_HOME',"
			+ "'ROLE_PESQUISA_RETORNO_LEITURA',"
			+ "'ROLE_IMPORTACAO',"
			+ "'ROLE_DISTRIBUICAO',"
			+ "'ROLE_LIBERACAO_CARGA',"
			//+ "'ROLE_LANCAMENTO_LEITURA',"
			+ "'ROLE_ACOMPANHAMENTO',"
			+ "'ROLE_ROTEIRO',"
			+ "'ROLE_EMPRESA',"
			+ "'ROLE_LOTE',"
			+ "'ROLE_OCORRENCIA',"
			+ "'ROLE_PERFIL_ACESSO',"
			+ "'ROLE_REGIONAL',"
			+ "'ROLE_USUARIO',"
			//+ "'ROLE_REPASSE',"
			+ "'ROLE_EXPORTACAO',"
			+ "'ROLE_AUDITORIA',"
			+ "'ROLE_JORNADA_COLABORADOR') AND CD_SITUACAO = 1", nativeQuery = true)
	public List<Permissao> getPermissoesCrudUser();
}
