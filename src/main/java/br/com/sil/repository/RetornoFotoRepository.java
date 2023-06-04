package br.com.sil.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.sil.model.RetornoFoto;
import br.com.sil.repository.projection.RetornoFotoProjection;
import br.com.sil.repository.retornofoto.RetornoFotoRepositoryQuery;

public interface RetornoFotoRepository extends JpaRepository<RetornoFoto, Long>, RetornoFotoRepositoryQuery {

	public Optional<RetornoFoto> findByNome(String nome);
	
	@Transactional
	@Query(value="SELECT TOP 1 * FROM RETORNO_FOTO WHERE NM_NOME = ?1 ", nativeQuery = true)
	public RetornoFoto isExist(String nome);
	
	@Transactional
	@Query(value="SELECT ID_RETORNO_FOTO AS ID, DS_PATH AS PATH FROM RETORNO_FOTO WHERE ID_LEITURA = ?1 ", nativeQuery = true)
	public List<RetornoFotoProjection> listar(Long idLeitura);
	
	
	@Transactional
	@Query(value="SELECT * FROM RETORNO_FOTO WHERE ID_LEITURA = ?1 ", nativeQuery = true)
	public List<RetornoFoto> getConsultaCliente(Long idLeitura);
	
	@Transactional
	@Query(value="SELECT LTRIM(RTRIM(DS_PATH)) FROM RETORNO_FOTO WHERE ID_RETORNO_FOTO = ?1", nativeQuery = true)
	public List<String> getPath(Long idRetornoFoto);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO LOG_RETORNO_FOTO (ID_RETORNO_FOTO, ID_USUARIO, DS_ALTERACAO, DT_ATUALIZACAO) VALUES (?1, ?2, ?3, ?4);", nativeQuery = true)
	public int setLogIncluirFoto(Long idRetornoFoto, Long idUsuario, String descricao, LocalDateTime dataAtualizacao);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO RETORNO_FOTO (ID_LEITURA, ID_USUARIO, NM_NOME, DS_PATH, DT_FOTO, CD_LATITUDE, CD_LONGITUDE, NR_INSTALACAO, NR_MEDIDOR, DS_IMAGEM, DS_MARCA, DS_MODELO) "
			+ "SELECT C.ID_LEITURA, ?2 AS IDUSUARIO, B.NM_NOME, B.DS_PATH, B.DT_FOTO, B.CD_LATITUDE, B.CD_LONGITUDE, C.NR_INSTALACAO, C.NR_MEDIDOR, B.DS_IMAGEM, B.DS_MARCA, B.DS_MODELO "
			+ "FROM MED_LEITURA AS A "
			+ "INNER JOIN RETORNO_FOTO AS B ON B.ID_LEITURA = A.ID_LEITURA "
			+ "INNER JOIN MED_LEITURA AS C ON C.NR_MEDIDOR = A.NR_MEDIDOR AND C.ID_LEITURA = ?1 "
			+ "LEFT OUTER JOIN RETORNO_FOTO AS B2 ON B2.ID_LEITURA = C.ID_LEITURA "
			+ "WHERE B.ID_LEITURA = ?3 AND B2.ID_LEITURA IS NULL", nativeQuery = true)
	public int incluirRepasse(long idLeituraRepasse, long idUsuario, long idLeituraPasse);
	
}
