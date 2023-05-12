package br.com.sil.repository.projection;

import java.time.LocalDate;

public interface AcessoSistemaUsuarioProjection {
	String getUsuario();
	String getPerfilAcesso();
	LocalDate getDataAcesso();
	String getPrimeiroAcesso();
	String getUltimoAcesso();
	String getIpAcesso();
	String getAcessoUltimoBrowser();
	Integer getQtdAcesso();
}
