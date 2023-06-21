package br.com.sil.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties("sil")
public class ApiProperty {

	private final Seguranca seguranca = new Seguranca();
	private final Mail mail = new Mail();
	private String originPermitida = "http://localhost";
	private String urlDesenv = "http://localhost:4200";
	private String urlMobile = "http://localhost";
	private String pathImportacao = "/opt/jboss/wildfly/standalone/tmp/sil/importacao/";
	private String pathExportacao = "/opt/jboss/wildfly/standalone/tmp/sil/exportacao/";
	private String pathFoto = "/opt/jboss/wildfly/standalone/tmp/sil/fotos/";
	private String urlProducao = "https://silgascomgas.grupofloripark.com.br";
	private String versao = "1.1.5";
	
	//private String pathImportacao = "/Users/fellipeaugustopires/Documents/SFTP/opt/jboss/wildfly/standalone/tmp/sil/importacao/";
	//private String pathExportacao = "/Users/fellipeaugustopires/Documents/SFTP/opt/jboss/wildfly/standalone/tmp/sil/exportacao/";
	//private String pathFoto = "/Users/fellipeaugustopires/Documents/SFTP/opt/jboss/wildfly/standalone/tmp/sil/fotos/";
	
	@Getter
	@Setter
	public static class Seguranca {

		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
	}
	
	@Getter
	@Setter
	public static class Mail{
		private String host;
		private Integer port;
		private String username;
		private String password;	
	}

}
