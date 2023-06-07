package br.com.sil.security.token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import br.com.sil.model.PerfilAcesso;
import br.com.sil.model.Regional;
import br.com.sil.security.UsuarioSistema;
import br.com.sil.service.PerfilAcessoService;
import br.com.sil.service.RegionalService;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Autowired
	private PerfilAcessoService perfilAcessoService;
	
	@Autowired
	private RegionalService regionalService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		UsuarioSistema usuarioSistema = (UsuarioSistema) authentication.getPrincipal();
		List<Regional> listaRegional = this.regionalService.getRegionaisPorUsuario(usuarioSistema.getUsuario().getId());
		List<PerfilAcesso> listaPerfilAcesso = this.perfilAcessoService.getPerfilPorUsuario(usuarioSistema.getUsuario().getId());
		Map<String, Object> addInfo = new HashMap<>();
		addInfo.put("id", usuarioSistema.getUsuario().getId());
		addInfo.put("listaRegional", listaRegional);
		addInfo.put("perfilAcesso", listaPerfilAcesso.get(0));
		addInfo.put("versao", "1.0.6");
		if (usuarioSistema.getUsuario().getSituacao() > 0 && !listaPerfilAcesso.get(0).getNome().equalsIgnoreCase("MOBILE")) {		
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
			return accessToken;
		} else {			
			return null;
		}
	}
}
