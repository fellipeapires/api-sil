package br.com.sil.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sil.mail.Mailer;
import br.com.sil.model.PerfilAcesso;
import br.com.sil.model.Usuario;
import br.com.sil.model.dto.AcessoDto;
import br.com.sil.repository.UsuarioRepository;
import br.com.sil.repository.filter.UsuarioFilter;
import br.com.sil.repository.projection.AcessoSistemaUsuarioProjection;
import br.com.sil.repository.projection.UsuarioMobileProjection;
import br.com.sil.service.interfaces.IUsuarioService;
import br.com.sil.util.Utility;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerfilAcessoService perfilAcessoService;
	
	@Autowired
	private PermissaoService permissaoService;

	@Autowired
	private Mailer mailer;
	
	@Autowired
	private Utility utility;

	public Optional<Usuario> findByLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}

	@Override
	public Usuario incluir(Usuario entity) {
		if (entity.getId() == null) {
			PerfilAcesso perfilAcesso = this.perfilAcessoService.buscarPorId(entity.getPerfilAcesso().getId());
			String senha = "";
			Usuario usuario = null;
			if (!perfilAcesso.getNome().equals("MOBILE")) {
				if (perfilAcesso.getNome().equals("CLIENTE"))  {
					entity.getPermissoes().get(0).setId(this.permissaoService.findByNome("ROLE_PAINEL_CLIENTE").get().getId());
				} else {
					entity.setPermissoes(this.permissaoService.getPermissoesCrudUser());
				}
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				senha = this.utility.gerarSenha(6);
				entity.setSenha(encoder.encode(senha.trim()));
				usuario = usuarioRepository.save(entity);
				this.enviarSenha(usuario, senha);
			} else {
				entity.getPermissoes().get(0).setId(this.permissaoService.findByNome("ROLE_MOBILE").get().getId());
				entity.setSenha(entity.getMatricula());
				usuario = usuarioRepository.save(entity);
			}
			usuario.setSenha("");
			return usuario;
		} else {
			return null;
		}
	}

	@Override
	public Usuario alterar(Usuario entity) {
		if (entity.getId() != null) {
			PerfilAcesso perfilAcesso = this.perfilAcessoService.buscarPorId(entity.getPerfilAcesso().getId());
			if (perfilAcesso.getNome().equals("MOBILE")) {
				entity.setSenha(entity.getMatricula());
			} else {
				entity.setSenha(buscarPorId(entity.getId()).getSenha());
			}
			Usuario usuario = usuarioRepository.save(entity);
			usuario.setSenha("");
			return usuario;
		} else {
			return null;
		}
	}

	public Usuario resetPassword(long id) {
		Usuario usuario = buscarPorId(id);
		if (usuario != null) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senha = this.utility.gerarSenha(6);
			usuario.setSenha(encoder.encode(senha.trim()));
			usuarioRepository.save(usuario);
			this.enviarSenha(usuario, senha);
			usuario.setSenha("");
			return usuario;
		} else {
			return null;
		}
	}
	
	public Integer registrarAcesso(AcessoDto entity) {
		return this.usuarioRepository.resgistrarAcesso(entity.getIdUsuario(), entity.getBrowser(), entity.getIp(), LocalDateTime.now());
	}

	@Override
	public Usuario buscarPorId(long id) {
		return this.usuarioRepository.findById(id).get();
	}

	@Override
	public List<Usuario> pesquisar(UsuarioFilter filter) {
		return this.usuarioRepository.pesquisar(filter);
	}

	private boolean enviarSenha(Usuario usuario, String senha) {
		List<Usuario> destinatarios = new ArrayList<>();
		destinatarios.add(usuario);
		this.mailer.enviarSenha(senha, usuario, destinatarios, "Login de Acesso");
		return true;
	}

	public Usuario alterarSenha(Usuario entity) {
		Usuario usuario = this.usuarioRepository.findById(entity.getId()).get();
		if (usuario != null) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			usuario.setSenha(encoder.encode(entity.getSenha()));
			usuario = this.usuarioRepository.save(usuario);
			usuario.setSenha("");
			return usuario;
		} else {
			return null;
		}
	}
	
	public List<UsuarioMobileProjection> getUsuariosMobile() {
		return this.usuarioRepository.getUsuariosMobile();
	}
	
	public List<AcessoSistemaUsuarioProjection> listarAcessoSistema(UsuarioFilter filter) {
		return this.usuarioRepository.listarAcessoSistema(filter.getIdUsuario(), filter.getDataReferencia());
		
	}

}
