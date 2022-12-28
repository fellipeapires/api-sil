package br.com.sil.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.sil.model.Usuario;
import br.com.sil.repository.usuario.UsuarioRepositoryQuery;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery {
	
	public Optional<Usuario> findByLogin(String login);
		
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO LOG_ACESSO (ID_USUARIO, NM_BROWSER, NR_IP, DT_ACESSO) VALUES (?1, ?2, ?3, ?4);", nativeQuery = true)
	public int resgistrarAcesso(Long idUsuario, String browser, String ip, LocalDateTime dataAcesso);
}
